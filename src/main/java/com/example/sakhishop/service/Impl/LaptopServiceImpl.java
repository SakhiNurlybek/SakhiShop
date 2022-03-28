package com.example.sakhishop.service.Impl;

import com.example.sakhishop.entity.laptop.*;
import com.example.sakhishop.repository.laptop.*;
import com.example.sakhishop.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private LaptopDetailsRepository laptopDetailsRepository;

    @Autowired
    private LaptopColorsRepository laptopColorsRepository;

    @Autowired
    private LaptopDriveTypeRepository laptopDriveTypeRepository;

    @Autowired
    private LaptopImageRepository laptopImageRepository;

    @Autowired
    private LaptopMemoryRepository laptopMemoryRepository;

    @Autowired
    private LaptopSystemRepository laptopSystemRepository;

    @Override
    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    @Override
    public Laptop getLaptop(Long id) {
        return laptopRepository.getById(id);
    }

    @Override
    public Laptop saveLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public void deleteLaptop(Laptop laptop) {
        laptopRepository.delete(laptop);
    }

    @Override
    public List<LaptopDetails> getAllLaptopDetails() {
        return laptopDetailsRepository.findAll();
    }

    @Override
    public LaptopDetails getLaptopDetails(Long id) {
        return laptopDetailsRepository.getById(id);
    }

    @Override
    public LaptopDetails saveLaptopDetails(LaptopDetails laptopDetails) {
        return laptopDetailsRepository.save(laptopDetails);
    }

    @Override
    public void deleteLaptopDetails(LaptopDetails laptopDetails) {
        laptopDetailsRepository.delete(laptopDetails);
    }

    @Override
    public List<LaptopColors> getAllLaptopColors() {
        return laptopColorsRepository.findAll();
    }

    @Override
    public LaptopColors getLaptopColor(Long id) {
        return laptopColorsRepository.getById(id);
    }

    @Override
    public LaptopColors saveLaptopColor(LaptopColors laptopColors) {
        return laptopColorsRepository.save(laptopColors);
    }

    @Override
    public void deleteLaptopColor(LaptopColors laptopColors) {
        laptopColorsRepository.delete(laptopColors);
    }

    @Override
    public List<LaptopDriveType> getAllLaptopDriveTypes() {
        return laptopDriveTypeRepository.findAll();
    }

    @Override
    public LaptopDriveType getLaptopDriveType(Long id) {
        return laptopDriveTypeRepository.getById(id);
    }

    @Override
    public LaptopDriveType saveLaptopDriveType(LaptopDriveType laptopDriveType) {
        return laptopDriveTypeRepository.save(laptopDriveType);
    }

    @Override
    public void deleteLaptopDriveType(LaptopDriveType laptopDriveType) {
        laptopDriveTypeRepository.delete(laptopDriveType);
    }

    @Override
    public List<LaptopImage> getAllLaptopImages() {
        return laptopImageRepository.findAll();
    }

    @Override
    public LaptopImage getLaptopImage(Long id) {
        return laptopImageRepository.getById(id);
    }

    @Override
    public LaptopImage saveLaptopImage(LaptopImage laptopImage) {
        return laptopImageRepository.save(laptopImage);
    }

    @Override
    public void deleteLaptopImage(LaptopImage laptopImage) {
        laptopImageRepository.delete(laptopImage);
    }

    @Override
    public List<LaptopMemory> getAllLaptopMemories() {
        return laptopMemoryRepository.findAll();
    }

    @Override
    public LaptopMemory getLaptopMemory(Long id) {
        return laptopMemoryRepository.getById(id);
    }

    @Override
    public LaptopMemory saveLaptopMemory(LaptopMemory laptopMemory) {
        return laptopMemoryRepository.save(laptopMemory);
    }

    @Override
    public void deleteLaptopMemory(LaptopMemory laptopMemory) {
        laptopMemoryRepository.delete(laptopMemory);
    }

    @Override
    public List<LaptopSystem> getAllLaptopSystems() {
        return laptopSystemRepository.findAll();
    }

    @Override
    public LaptopSystem getLaptopSystem(Long id) {
        return laptopSystemRepository.getById(id);
    }

    @Override
    public LaptopSystem saveLaptopSystem(LaptopSystem laptopSystem) {
        return laptopSystemRepository.save(laptopSystem);
    }

    @Override
    public void deleteLaptopSystem(LaptopSystem laptopSystem) {
        laptopSystemRepository.delete(laptopSystem);
    }
}
