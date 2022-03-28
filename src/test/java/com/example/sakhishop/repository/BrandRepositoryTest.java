package com.example.sakhishop.repository;

import com.example.sakhishop.IntegrationTestBase;
import com.example.sakhishop.entity.BrandEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


class BrandRepositoryTest extends IntegrationTestBase {

    private static final Long Apple_Id = 1L;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    void testGetById(){
        Optional<BrandEntity> brand = brandRepository.findById(Apple_Id);
        assertTrue(brand.isPresent());
    }

}