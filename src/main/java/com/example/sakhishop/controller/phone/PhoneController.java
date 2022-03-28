package com.example.sakhishop.controller.phone;

import com.example.sakhishop.entity.BrandEntity;
import com.example.sakhishop.entity.ColorEntity;
import com.example.sakhishop.entity.phone.MemoryEntity;
import com.example.sakhishop.entity.phone.Phone;
import com.example.sakhishop.entity.phone.PhoneDetails;
import com.example.sakhishop.service.MainService;
import com.example.sakhishop.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private MainService mainService;

    @GetMapping(value = "/")
    public String phones(Model model,
                         @Param(value = "keyword")String keyword,
                         @Param(value = "price_min")Integer min,
                         @Param(value = "price_max")Integer max,
                         @Param(value = "brand_name")String brand,
                         @Param(value = "color_name")String color,
                         @Param(value = "min_year")Integer minYear,
                         @Param(value = "max_year")Integer maxYear,
                         @Param(value = "memory")Integer memory){

        return listByPage(model,1,"price","desc",keyword,min,max,brand,color,minYear,maxYear,memory);
    }

    @GetMapping(value = "/page/{pageNo}")
    public String listByPage(Model model,
                             @PathVariable(name = "pageNo")Integer pageNo,
                             @Param(value = "sortField")String sortField,
                             @Param(value = "sortDir")String sortDir,
                             @Param(value = "keyword")String keyword,
                             @Param(value = "price_min")Integer min,
                             @Param(value = "price_max")Integer max,
                             @Param(value = "brand")String brand,
                             @Param(value = "color")String color,
                             @Param(value = "minYear")Integer minYear,
                             @Param(value = "maxYer")Integer maxYear,
                             @Param(value = "memory")Integer memory){

        Page<Phone> page = phoneService.search(pageNo,sortField,sortDir,min,max,brand,color,minYear,maxYear,memory);
        List<Phone> phones = page.getContent();

        if(keyword!=null){
            page = phoneService.getAllPhonesPage(pageNo,sortField,sortDir,keyword);
            phones = page.getContent();
        }

        List<BrandEntity> brands = mainService.getAllBrands();
        List<ColorEntity> colors = mainService.getAllColors();
        List<MemoryEntity> memories = phoneService.getAllMemories();

        long totalItems = page.getTotalElements();
        int totalPages =  page.getTotalPages();

        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("phones",phones);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("keyword",keyword);
        model.addAttribute("min",min);
        model.addAttribute("max",max);
        model.addAttribute("brands",brands);
        model.addAttribute("colors",colors);
        model.addAttribute("memories",memories);
        model.addAttribute("brand",brand);
        model.addAttribute("color",color);
        model.addAttribute("minYear",minYear);
        model.addAttribute("maxYear",maxYear);
        model.addAttribute("memory",memory);

        return "phone/phones";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model,
                          @PathVariable(name = "id") Long phoneId){

        Phone phone = phoneService.getPhone(phoneId);
        model.addAttribute("phone",phone);

        return "phone/details";
    }

//    @GetMapping(value = "/search/{pageNo}")
//    public String search(@PathVariable(name = "pageNo")Integer pageNo,
//                         Model model,
//                         @Param(value = "price_min")Integer min,
//                         @Param(value = "price_max")Integer max,
//                         @Param(value = "sortField")String sortField,
//                         @Param(value = "sortDir")String sortDir,
//                         @Param(value = "brand")String brand,
//                         @Param(value = "color")String color){
//
//        Page<Phone> page = phoneService.search(pageNo,sortField,sortDir,min,max,brand,color);
//        List<Phone> phones = page.getContent();
//
//        model.addAttribute("currentPage",pageNo);
//        model.addAttribute("totalPages",page.getTotalPages());
//        model.addAttribute("totalItems",page.getTotalElements());
//        model.addAttribute("phones",phones);
//        model.addAttribute("min",min);
//        model.addAttribute("max",max);
//
//        return "phone/search";
//    }

}
