package com.example.sakhishop.controller.admin;

import com.example.sakhishop.entity.Roles;
import com.example.sakhishop.entity.Users;
import com.example.sakhishop.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Value("${file.avatar.uploadPath}")
    private String uploadPath;

    @GetMapping(value = "/")
    public String admin(Model model){

        model.addAttribute("currentUser",getUserData());

        return "admin/admin";
    }

    @GetMapping(value = "/users")
    public String users(Model model){

        List<Users> users = userService.getAllUsers();
        model.addAttribute("users",users);

        return "admin/users";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUser(
            Model model,
            @PathVariable(name = "id")Long id){

        Users user = userService.getUser(id);
        model.addAttribute("user",user);

        List<Roles> roles = userService.getAllRoles();
        roles.removeAll(user.getRoles());
        model.addAttribute("roles",roles);

        return "admin/editUser";
    }

    @PostMapping(value = "/save")
    public String saveUser(
            @RequestParam(name = "user_id")Long userId,
            @RequestParam(name = "first_name")String firstName,
            @RequestParam(name = "last_name")String lastName,
            @RequestParam(name = "phone_number")long phoneNumber,
            @RequestParam(name = "birthday")String birthday,
            @RequestParam(name = "location") String location){

        Users user = userService.getUser(userId);
        if(user!=null){
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setBirthday(birthday);
            user.setBirthday(birthday);
            user.setLocation(location);
            userService.saveUser(user);
        }
        return "redirect:/admin/users/";
    }

    @PostMapping(value = "/assignrole")
    public String assignRole(
            @RequestParam(name = "role_id")Long roleId,
            @RequestParam(name = "user_id")Long userId){

        Roles role = userService.getRole(roleId);
        if(role!=null){
            Users user = userService.getUser(userId);
            if(user!=null){
                List<Roles> roles = user.getRoles();
                if(roles==null){
                    roles = new ArrayList<>();
                }
                roles.add(role);
                userService.saveUser(user);

                return "redirect:/admin/edit/" + userId;
            }
        }
        return "redirect:/admin/users/";
    }

    @PostMapping(value = "/unassignrole")
    public String unAssignRole(
            @RequestParam(name = "role_id")Long roleId,
            @RequestParam(name = "user_id")Long userId){

        Roles role = userService.getRole(roleId);
        if(role!=null){
            Users user = userService.getUser(userId);
            if(user!=null){
                List<Roles> roles = user.getRoles();
                if(roles==null){
                    roles = new ArrayList<>();
                }
                roles.remove(role);
                userService.saveUser(user);

                return "redirect:/admin/edit/" + userId;
            }
        }
        return "redirect:/admin/users/";
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam(name = "user_ava") MultipartFile file,
                         @RequestParam(name = "user_id") Long id){

        Users user = userService.getUser(id);
        if(user!=null) {
            if (file.getContentType().equals("image/jpg") || file.getContentType().equals("image/png")) {
                try {
                    String avatar = DigestUtils.sha1Hex("image_" + user.getId() + "_image");
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(uploadPath + avatar + ".png");
                    Files.write(path, bytes);
                    user.setAvatar(avatar);
                    userService.saveUser(user);

                    return "redirect:/admin/edit/" + id;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/";
    }

    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            Users myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }
}
