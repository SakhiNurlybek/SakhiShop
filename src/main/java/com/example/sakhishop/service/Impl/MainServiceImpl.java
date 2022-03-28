package com.example.sakhishop.service.Impl;

import com.example.sakhishop.entity.BrandEntity;
import com.example.sakhishop.entity.ColorEntity;
import com.example.sakhishop.repository.BrandRepository;
import com.example.sakhishop.repository.ColorRepository;
import com.example.sakhishop.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<BrandEntity> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public BrandEntity getBrand(Long id) {
        return brandRepository.getById(id);
    }

    @Override
    public BrandEntity addBrand(BrandEntity brand) {
        return brandRepository.save(brand);
    }

    @Override
    public BrandEntity saveBrand(BrandEntity brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(BrandEntity brand) {
        brandRepository.delete(brand);
    }

    @Override
    public List<ColorEntity> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public ColorEntity getColor(Long id) {
        return colorRepository.getById(id);
    }

    @Override
    public ColorEntity addColor(ColorEntity color) {
        return colorRepository.save(color);
    }

    @Override
    public ColorEntity saveColor(ColorEntity color) {
        return colorRepository.save(color);
    }

    @Override
    public void deleteColor(ColorEntity color) {
        colorRepository.delete(color);
    }
}
