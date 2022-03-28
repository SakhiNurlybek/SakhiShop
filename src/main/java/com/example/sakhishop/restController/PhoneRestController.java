package com.example.sakhishop.restController;

import com.example.sakhishop.entity.phone.PhoneDetails;
import com.example.sakhishop.exception.ResourseNotFoundException;
import com.example.sakhishop.repository.phone.PhoneDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PhoneRestController {

    @Autowired
    private PhoneDetailsRepository phoneRepository;

    @GetMapping("phones")
    public List<PhoneDetails> getAllPhones(){
        return this.phoneRepository.findAll();
    }
    
    @GetMapping("/phones/{id}")
    public ResponseEntity<PhoneDetails> getPhoneById(
            @PathVariable(value = "id")Long phoneId) throws ResourseNotFoundException {

        PhoneDetails phone = phoneRepository.findById(phoneId).
                orElseThrow(()-> new ResourseNotFoundException("Phone not found with this id ::" + phoneId));
        return ResponseEntity.ok().body(phone);
    }

    @PostMapping("phones")
    public PhoneDetails createPhone(
            @RequestBody PhoneDetails phone){

        return this.phoneRepository.save(phone);
    }

    @PutMapping("phones/{id}")
    @ResponseBody
    public ResponseEntity<PhoneDetails> updatePhone(
            @PathVariable(value = "id")Long phoneId,
            @Validated @RequestBody PhoneDetails phoneDetails) throws ResourseNotFoundException{

        PhoneDetails phone = phoneRepository.findById(phoneId).
                orElseThrow(()-> new ResourseNotFoundException("Phone not found with this id ::" + phoneId));

        phone.setName(phoneDetails.getName());
        phone.setYear(phoneDetails.getYear());
        phone.setRam(phoneDetails.getRam());
        phone.setDisplay(phoneDetails.getDisplay());
        phone.setHeight(phoneDetails.getHeight());
        phone.setBrand(phoneDetails.getBrand());
        return ResponseEntity.ok().body(this.phoneRepository.save(phone));
    }
}
