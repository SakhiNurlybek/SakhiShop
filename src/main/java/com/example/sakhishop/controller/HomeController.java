package com.example.sakhishop.controller;

import com.example.sakhishop.entity.Users;
import com.example.sakhishop.entity.phone.Phone;
import com.example.sakhishop.service.PhoneService;
import com.example.sakhishop.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Value("${file.avatar.uploadPath}")
    private String uploadPath;

    @Value("${file.avatar.viewPath}")
    private String viewPath;

    @Value("${file.avatar.default}")
    private String defaultImg;

    @GetMapping(value = "/")
    public String home(Model model){

        model.addAttribute("currentUser",getUserData());

        return "index";
    }

    @GetMapping(value = "/login")
    public String login(){

        return "security/login";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){

        model.addAttribute("currentUser",getUserData());

        return "security/profile";
    }


    @GetMapping(value = "/register")
    public String register(){

        return "security/register";
    }

    @PostMapping(value = "/register")
    public String toRegister(
            @RequestParam(name = "user_email")String email,
            @RequestParam(name = "user_password")String password,
            @RequestParam(name = "user_re_password")String rePassword,
            @RequestParam(name = "user_first_name")String firstName,
            @RequestParam(name = "user_last_name")String lastName,
            @RequestParam(name = "user_phone")long phone,
            @RequestParam(name = "user_birthday") String date,
            @RequestParam(name = "user_location")String location){

        if(password.equals(rePassword)){
            Users newUser = new Users();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setPhoneNumber(phone);
            newUser.setBirthday(date);
            newUser.setLocation(location);

            if(userService.createUser(newUser)!=null){
                return "redirect:/login";
            }
        }
        return "redirect:/register?error";
    }

    @GetMapping(value = "/edit_profile")
    @PreAuthorize("isAuthenticated()")
    public String editProfile(Model model){

        model.addAttribute("currentUser",getUserData());

        return "security/editProfile";
    }

    @PostMapping(value = "save_profile")
    @PreAuthorize("isAuthenticated()")
    public String saveProfile(
            @RequestParam(name = "user_first_name")String firstName,
            @RequestParam(name = "user_last_name")String lastName,
            @RequestParam(name = "user_phone")long phone,
            @RequestParam(name = "user_birthday") String date,
            @RequestParam(name = "user_location")String location){

        Users user = getUserData();
        if(user!=null){
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phone);
            user.setBirthday(date);
            user.setLocation(location);
        }
        userService.saveUser(user);

        return "redirect:/profile";
    }

    @GetMapping(value = "/403")
    public String accessDenied(){

        return "security/403";
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam(name = "user_ava")MultipartFile file){

        if(file.getContentType().equals("image/jpg") || file.getContentType().equals("image/png")){
            try{
                Users currentUser = getUserData();
                String avatar = DigestUtils.sha1Hex("image_" + currentUser.getId() + "_image");
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + avatar+".png");
                Files.write(path, bytes);
                currentUser.setAvatar(avatar);
                userService.saveUser(currentUser);

                return "redirect:/profile?success";

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/";
    }

    @GetMapping(value = "/view/{url}",produces = {MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] view(@PathVariable(name = "url")String url) throws IOException{

        String avatar = viewPath + defaultImg;

        if(url!=null && !url.isEmpty()){
            avatar = viewPath + url + ".png";
        }

        InputStream in;

        try{
            ClassPathResource resource = new ClassPathResource(avatar);
            in = resource.getInputStream();

        }catch (Exception e){
            ClassPathResource resource = new ClassPathResource(viewPath + defaultImg);
            in = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(in);
    }

    private Users getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User secUser = (User) authentication.getPrincipal();
            Users myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }
}
