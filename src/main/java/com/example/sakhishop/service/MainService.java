package com.example.sakhishop.service;

import com.example.sakhishop.entity.BrandEntity;
import com.example.sakhishop.entity.ColorEntity;

import java.util.List;

public interface MainService {

    List<BrandEntity> getAllBrands();
    BrandEntity getBrand(Long id);
    BrandEntity addBrand(BrandEntity brand);
    BrandEntity saveBrand(BrandEntity brand);
    void deleteBrand(BrandEntity brand);

    List<ColorEntity> getAllColors();
    ColorEntity getColor(Long id);
    ColorEntity addColor(ColorEntity color);
    ColorEntity saveColor(ColorEntity color);
    void deleteColor(ColorEntity color);
}
