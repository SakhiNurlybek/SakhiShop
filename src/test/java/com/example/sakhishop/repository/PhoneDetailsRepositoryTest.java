package com.example.sakhishop.repository;

import com.example.sakhishop.IntegrationTestBase;
import com.example.sakhishop.entity.BrandEntity;
import com.example.sakhishop.entity.phone.PhoneDetails;
import com.example.sakhishop.repository.phone.PhoneDetailsRepository;
import com.example.sakhishop.service.MainService;
import com.example.sakhishop.service.PhoneService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


class PhoneDetailsRepositoryTest extends IntegrationTestBase {

    private static final Long Id = 1L;
    private static final Long Apple_Id = 1L;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PhoneDetailsRepository phoneDetailsRepository;

    @Autowired
    private MainService mainService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetById(){
        Optional<PhoneDetails> phone = phoneDetailsRepository.findById(Id);
        assertTrue(phone.isPresent());
        phone.ifPresent(phoneEntity -> {
            assertEquals("Iphone 13 Pro",phoneEntity.getName());
        });
    }

    @Test
    void testSave(){
        PhoneDetails phone = PhoneDetails.builder()
                .name("Iphone 12")
                .year(2020)
                .height(146.7)
                .display("2532x1170")
                .ram(4)
                .build();
        phoneDetailsRepository.save(phone);
        assertNotNull(phone.getId());
    }



    @Test
    void testSaves(){
        PhoneDetails phone = PhoneDetails.builder()
                .name("Iphone 12")
                .year(2020)
                .height(146.7)
                .display("2532x1170")
                .ram(4)
                .build();
        phoneDetailsRepository.save(phone);
        assertNotNull(phone.getId());
    }




    @Test
    void testSetBrand(){
        PhoneDetails phone = phoneService.getPhoneDetails(Id);
        BrandEntity brand = mainService.getBrand(Apple_Id);
        phone.setBrand(brand);
        assertNotNull(phone.getBrand());
    }
}