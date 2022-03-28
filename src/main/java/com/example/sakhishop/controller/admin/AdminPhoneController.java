package com.example.sakhishop.controller.admin;

import com.example.sakhishop.entity.BrandEntity;
import com.example.sakhishop.entity.ColorEntity;
import com.example.sakhishop.entity.Users;
import com.example.sakhishop.entity.phone.ImageEntity;
import com.example.sakhishop.entity.phone.MemoryEntity;
import com.example.sakhishop.entity.phone.Phone;
import com.example.sakhishop.entity.phone.PhoneDetails;
import com.example.sakhishop.service.MainService;
import com.example.sakhishop.service.PhoneService;
import com.example.sakhishop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/phone")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
public class AdminPhoneController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private MainService mainService;

    @GetMapping(value = "/")
    public String phone(Model model,
                        @Param(value = "keyword") String keyword){

        List<PhoneDetails> phoneDetails = phoneService.getAllPhonesDetails();
        model.addAttribute("phoneDetails",phoneDetails);

        List<Phone> phones = phoneService.getAllPhones();
        model.addAttribute("phones",phones);

        return "admin/phone/phone";
    }

    @GetMapping(value = "/add")
    public String addPhone(Model model){

        List<BrandEntity> brandList = mainService.getAllBrands();
        model.addAttribute("brandList",brandList);

        List<ColorEntity> colorList = mainService.getAllColors();
        model.addAttribute("colorList",colorList);

        List<MemoryEntity> memoryList = phoneService.getAllMemories();
        model.addAttribute("memoryList",memoryList);

        return "admin/phone/addPhone";
    }

    @PostMapping(value = "/add")
    public String addToPhone(
            @RequestParam(name = "phone_name")String name,
            @RequestParam(name = "phone_year")int year,
            @RequestParam(name = "phone_height")double height,
            @RequestParam(name = "phone_display")String display,
            @RequestParam(name = "phone_ram")int ram,
            @RequestParam(name = "phone_amount[]")List<Integer> amounts,
            @RequestParam(name = "phone_price[]")List<Integer> prices,
            @RequestParam(name = "phone_brand")Long brandId,
            @RequestParam(name = "phone_memory")Long memoryId,
            @RequestParam(name = "phone_colors[]")List<Long>colors,
            @RequestParam(name = "phone_images[]")List<String>images){

        PhoneDetails phoneDetails = new PhoneDetails();
        phoneDetails.setName(name);
        phoneDetails.setYear(year);
        phoneDetails.setHeight(height);
        phoneDetails.setDisplay(display);
        phoneDetails.setRam(ram);
        BrandEntity brand = mainService.getBrand(brandId);
        phoneDetails.setBrand(brand);
        MemoryEntity memory = phoneService.getMemory(memoryId);
        phoneService.savePhoneDetails(phoneDetails);
        for (int i = 0; i < colors.size(); i++) {
           ColorEntity color = mainService.getColor(colors.get(i));
           String image = images.get(i);
           int price = prices.get(i);
           int amount = amounts.get(i);
           if(color!=null){
               Phone phone = new Phone();
               phone.setMemory(memory);
               phone.setAmount(amount);
               phone.setPrice(price);
               phone.setColor(color);
               phone.setImage(image);
               phone.setPhone(phoneDetails);
               phoneService.savePhone(phone);
           }
        }

        return "redirect:/admin/phone/";
    }

    @PostMapping(value = "/save_details")
    public String saveDetails(
            @RequestParam(name = "details_id")Long detailsId,
            @RequestParam(name = "brand_id")Long brandId,
            @RequestParam(name = "phone_name")String name,
            @RequestParam(name = "phone_year")int year,
            @RequestParam(name = "phone_height") double height,
            @RequestParam(name = "phone_display")String display,
            @RequestParam(name = "phone_ram")int ram){

        PhoneDetails phoneDetails = phoneService.getPhoneDetails(detailsId);
        if(phoneDetails!=null){
            BrandEntity brand = mainService.getBrand(brandId);
            if(brand!=null){
                phoneDetails.setBrand(brand);
                phoneDetails.setName(name);
                phoneDetails.setYear(year);
                phoneDetails.setHeight(height);
                phoneDetails.setDisplay(display);
                phoneDetails.setRam(ram);
                phoneService.savePhoneDetails(phoneDetails);
            }
        }
        return "redirect:/admin/phone/";
    }

    @PostMapping(value = "/save")
    public String save(
            @RequestParam(name = "phone_id")Long phoneId,
            @RequestParam(name = "brand_id")Long brandId,
            @RequestParam(name = "color_id")Long colorId,
            @RequestParam(name = "memory_id")Long memoryId,
            @RequestParam(name = "phone_name")String name,
            @RequestParam(name = "phone_year")int year,
            @RequestParam(name = "phone_height") double height,
            @RequestParam(name = "phone_display")String display,
            @RequestParam(name = "phone_ram")int ram,
            @RequestParam(name = "phone_price")int price,
            @RequestParam(name = "phone_amount")int amount,
            @RequestParam(name = "phone_image")String image){

        Phone phone = phoneService.getPhone(phoneId);
        if (phone != null) {
            PhoneDetails details = phone.getPhone();
            if (details != null) {
                BrandEntity brand = mainService.getBrand(brandId);
                if (brand != null) {
                    ColorEntity color = mainService.getColor(colorId);
                    if (color != null) {
                        MemoryEntity memory = phoneService.getMemory(memoryId);
                        if (memory != null) {
                            details.setBrand(brand);
                            details.setName(name);
                            details.setYear(year);
                            details.setHeight(height);
                            details.setDisplay(display);
                            details.setRam(ram);
                            phone.setPhone(details);
                            phone.setMemory(memory);
                            phone.setColor(color);
                            phone.setAmount(amount);
                            phone.setPrice(price);
                            phone.setImage(image);
                            phoneService.savePhone(phone);
                        }
                    }
                }
            }
        }
        return "redirect:/admin/phone/";
    }

    @PostMapping(value = "/delete_details")
    public String deleteDetails(
            @RequestParam(name = "details_id")Long detailsId){
        PhoneDetails phoneDetails = phoneService.getPhoneDetails(detailsId);
        if(phoneDetails!=null){
            phoneService.deletePhoneDetails(phoneDetails);
        }
        return "redirect:/admin/phone/";
    }

    @PostMapping(value = "/delete")
    public String delete(
            @RequestParam(name = "phone_id")Long phoneId){
        Phone phone = phoneService.getPhone(phoneId);
        if(phone!=null){
            phoneService.deletePhone(phone);
        }
        return "redirect:/admin/phone/";
    }

    @GetMapping(value = "/edit_details/{id}")
    public String editDetails(Model model,
                          @PathVariable(name = "id")Long id){

        PhoneDetails phoneDetails = phoneService.getPhoneDetails(id);
        model.addAttribute("phoneDetails",phoneDetails);

        List<BrandEntity> brands = mainService.getAllBrands();
        model.addAttribute("brands",brands);

        List<ColorEntity> colors = mainService.getAllColors();
        colors.removeAll(phoneDetails.getColors());
        model.addAttribute("colors",colors);

        List<MemoryEntity> memories = phoneService.getAllMemories();
        model.addAttribute("memories",memories);

        List<Phone> phones = phoneService.findByPhone(phoneDetails);
        model.addAttribute("phones",phones);

        return "admin/phone/editPhone";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(Model model,
                       @PathVariable(name = "id")Long id){

        Phone phone = phoneService.getPhone(id);
        model.addAttribute("phone",phone);

        List<BrandEntity> brands = mainService.getAllBrands();
        model.addAttribute("brands",brands);

        List<ColorEntity> colors = mainService.getAllColors();
        model.addAttribute("colors",colors);

        List<MemoryEntity> memories = phoneService.getAllMemories();
        model.addAttribute("memories",memories);

        List<ImageEntity> images = phone.getImages();
        model.addAttribute("images",images);

        return "admin/phone/edit";
    }

    @PostMapping(value = "/add_images")
    public String addImages(
            @RequestParam(name = "phone_id")Long id,
            @RequestParam(name = "phone_image")String image){

        Phone phone = phoneService.getPhone(id);
        if(phone!=null){
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(image);
            imageEntity.setPhone(phone);
            phoneService.saveImage(imageEntity);
            return "redirect:/admin/phone/edit/" +id;
        }
        return "redirect:/admin/phone/";
    }

    @PostMapping(value = "/delete_images")
    public String deleteImages(@RequestParam("phone_id")Long phoneId,
                               @RequestParam("image_id")Long imageId){

        Phone phone = phoneService.getPhone(phoneId);
        if(phone!=null){
            ImageEntity image = phoneService.getImage(imageId);
            if(image!=null){
                phoneService.deleteImage(image);
            }
        }
        phoneService.savePhone(phone);
        return "redirect:/admin/phone/edit/" +phoneId;
    }

    @PostMapping(value = "/assigncolor")
    public String assigncolor(
            @RequestParam(name = "details_id")Long detailsId,
            @RequestParam(name = "color_id") Long colorId,
            @RequestParam(name = "memory_id")Long memoryId,
            @RequestParam(name = "phone_price")int price,
            @RequestParam(name = "phone_amount")int amount,
            @RequestParam(name = "phone_image")String image){

        ColorEntity color = mainService.getColor(colorId);
        if(color!=null){
            PhoneDetails phoneDetails = phoneService.getPhoneDetails(detailsId);
            if (phoneDetails!=null){
                MemoryEntity memory = phoneService.getMemory(memoryId);
                if(memory!=null){
                    Phone phone = new Phone();
                    phone.setPhone(phoneDetails);
                    phone.setColor(color);
                    phone.setMemory(memory);
                    phone.setPrice(price);
                    phone.setAmount(amount);
                    phone.setImage(image);
                    phoneService.savePhone(phone);
                    return "redirect:/admin/phone/edit_details/"+detailsId;
                }
            }
        }
        return "redirect:/admin/phone/";
    }

    @PostMapping(value = "/unassigncolor")
    public String unassigncolor(
            @RequestParam(name = "phone_id")Long phoneId,
            @RequestParam(name = "details_id")Long detailsId){

        PhoneDetails phoneDetails = phoneService.getPhoneDetails(detailsId);
        Phone phone = phoneService.getPhone(phoneId);
        if(phone!=null){
            phoneService.deletePhone(phone);
        }
        phoneService.savePhoneDetails(phoneDetails);

        return "redirect:/admin/phone/edit_details/"+detailsId;
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
