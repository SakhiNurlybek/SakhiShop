package com.example.sakhishop.restController;

import com.example.sakhishop.entity.laptop.Laptop;
import com.example.sakhishop.entity.laptop.LaptopDetails;
import com.example.sakhishop.entity.phone.PhoneDetails;
import com.example.sakhishop.exception.ResourseNotFoundException;
import com.example.sakhishop.repository.laptop.LaptopDetailsRepository;
import com.example.sakhishop.repository.laptop.LaptopDriveTypeRepository;
import com.example.sakhishop.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class LaptopRestController {

    @Autowired
    private LaptopDetailsRepository laptopDetailsRepository;

    @GetMapping("laptops")
    public List<LaptopDetails> getAllLaptops(){
        return this.laptopDetailsRepository.findAll();
    }

    @GetMapping("/laptops/{id}")
    public ResponseEntity<LaptopDetails> getLaptopById(
            @PathVariable(value = "id")Long laptopId) throws ResourseNotFoundException {

        LaptopDetails laptopDetails = laptopDetailsRepository.findById(laptopId).
                orElseThrow(()-> new ResourseNotFoundException("Phone not found with this id ::" + laptopId));
        return ResponseEntity.ok().body(laptopDetails);
    }

    @PostMapping("laptops")
    public LaptopDetails createLaptop(
            @RequestBody LaptopDetails laptop){

        return this.laptopDetailsRepository.save(laptop);
    }

    @PutMapping("laptops/{id}")
    @ResponseBody
    public ResponseEntity<LaptopDetails> updateLaptop(
            @PathVariable(value = "id")Long laptopId,
            @Validated @RequestBody LaptopDetails laptopDetails) throws ResourseNotFoundException{

        LaptopDetails laptop = laptopDetailsRepository.findById(laptopId).
                orElseThrow(()-> new ResourseNotFoundException("Phone not found with this id ::" + laptopId));

        laptop.setName(laptopDetails.getName());
        laptop.setYear(laptopDetails.getYear());
        laptop.setDisplay(laptopDetails.getDisplay());
        return ResponseEntity.ok().body(this.laptopDetailsRepository.save(laptop));
    }
}
