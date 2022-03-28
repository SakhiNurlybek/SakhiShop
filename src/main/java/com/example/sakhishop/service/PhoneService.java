package com.example.sakhishop.service;

import com.example.sakhishop.entity.phone.ImageEntity;
import com.example.sakhishop.entity.phone.MemoryEntity;
import com.example.sakhishop.entity.phone.Phone;
import com.example.sakhishop.entity.phone.PhoneDetails;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PhoneService {

    List<PhoneDetails> getAllPhonesDetails();
    PhoneDetails getPhoneDetails(Long id);
    PhoneDetails addPhoneDetails(PhoneDetails phoneDetails);
    PhoneDetails savePhoneDetails(PhoneDetails phoneDetails);
    void deletePhoneDetails(PhoneDetails phoneDetails);

    List<Phone> getAllPhones();
    Phone getPhone(Long id);
    Phone addPhone(Phone phone);
    Phone savePhone(Phone phone);
    void deletePhone(Phone phone);
    Page<Phone> getAllPhonesPage(Integer pageNo,String sortField,String sortDir,String keyword);
    Page<Phone> findPaginated(Integer pageNo);
    Page<Phone> search(Integer pageNo,String sortField, String sortDir,Integer min,Integer max,String brand,String color,Integer minYear,Integer maxYear,Integer memory);
    List<Phone> findByPhone(PhoneDetails phoneDetails);

    List<MemoryEntity> getAllMemories();
    MemoryEntity getMemory(Long id);
    MemoryEntity addMemory(MemoryEntity memory);
    MemoryEntity saveMemory(MemoryEntity memory);
    void deleteMemory(MemoryEntity memory);

    List<ImageEntity> getAllImages();
    ImageEntity getImage(Long id);
    ImageEntity addImage(ImageEntity image);
    ImageEntity saveImage(ImageEntity image);
    void deleteImage(ImageEntity image);

}

