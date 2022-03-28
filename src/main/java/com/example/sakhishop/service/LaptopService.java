package com.example.sakhishop.service;

import com.example.sakhishop.entity.laptop.*;

import java.util.List;

public interface LaptopService {

    List<Laptop> getAllLaptops();
    Laptop getLaptop(Long id);
    Laptop saveLaptop(Laptop laptop);
    void deleteLaptop(Laptop laptop);

    List<LaptopDetails> getAllLaptopDetails();
    LaptopDetails getLaptopDetails(Long id);
    LaptopDetails saveLaptopDetails(LaptopDetails laptopDetails);
    void deleteLaptopDetails(LaptopDetails laptopDetails);

    List<LaptopColors> getAllLaptopColors();
    LaptopColors getLaptopColor(Long id);
    LaptopColors saveLaptopColor(LaptopColors laptopColors);
    void deleteLaptopColor(LaptopColors laptopColors);

    List<LaptopDriveType> getAllLaptopDriveTypes();
    LaptopDriveType getLaptopDriveType(Long id);
    LaptopDriveType saveLaptopDriveType(LaptopDriveType laptopDriveType);
    void deleteLaptopDriveType(LaptopDriveType laptopDriveType);

    List<LaptopImage> getAllLaptopImages();
    LaptopImage getLaptopImage(Long id);
    LaptopImage saveLaptopImage(LaptopImage laptopImage);
    void deleteLaptopImage(LaptopImage laptopImage);

    List<LaptopMemory> getAllLaptopMemories();
    LaptopMemory getLaptopMemory(Long id);
    LaptopMemory saveLaptopMemory(LaptopMemory laptopMemory);
    void deleteLaptopMemory(LaptopMemory laptopMemory);

    List<LaptopSystem> getAllLaptopSystems();
    LaptopSystem getLaptopSystem(Long id);
    LaptopSystem saveLaptopSystem(LaptopSystem laptopSystem);
    void deleteLaptopSystem(LaptopSystem laptopSystem);


}
