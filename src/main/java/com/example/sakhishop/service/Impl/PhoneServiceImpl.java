package com.example.sakhishop.service.Impl;

import com.example.sakhishop.entity.phone.ImageEntity;
import com.example.sakhishop.entity.phone.MemoryEntity;
import com.example.sakhishop.entity.phone.Phone;
import com.example.sakhishop.entity.phone.PhoneDetails;
import com.example.sakhishop.repository.phone.ImageRepositroy;
import com.example.sakhishop.repository.phone.MemoryRepository;
import com.example.sakhishop.repository.phone.PhoneDetailsRepository;
import com.example.sakhishop.repository.phone.PhoneRepository;
import com.example.sakhishop.service.PhoneService;
import com.example.sakhishop.specification.PhoneSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDetailsRepository phoneDetailsRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private MemoryRepository memoryRepository;

    @Autowired
    private ImageRepositroy imageRepositroy;

    @Override
    public List<PhoneDetails> getAllPhonesDetails() {
        return phoneDetailsRepository.findAll();
    }

    @Override
    public PhoneDetails getPhoneDetails(Long id) {
        return phoneDetailsRepository.getById(id);
    }

    @Override
    public PhoneDetails addPhoneDetails(PhoneDetails phoneDetails) {
        return phoneDetailsRepository.save(phoneDetails);
    }

    @Override
    public PhoneDetails savePhoneDetails(PhoneDetails phoneDetails) {
        return phoneDetailsRepository.save(phoneDetails);
    }

    @Override
    public void deletePhoneDetails(PhoneDetails phoneDetails) {
        phoneDetailsRepository.delete(phoneDetails);
    }

    @Override
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    @Override
    public Page<Phone> getAllPhonesPage(Integer pageNo, String sortField, String sortDir,String keyword) {

        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo - 1,9,sort);

        if (keyword!=null){
            return phoneRepository.findAllPhones(keyword,pageable);
        }
        return phoneRepository.findAll(pageable);
    }

    @Override
    public Page<Phone> findPaginated(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1,9);
        return phoneRepository.findAll(pageable);
    }

    @Override
    public Page<Phone> search(Integer pageNo, String sortField, String sortDir, Integer min, Integer max, String brand, String color,Integer minYear,Integer maxYear,Integer memory) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo - 1,9,sort);
        Specification<Phone> spec = PhoneSpecification.getSpec(min, max, brand, color,minYear,maxYear,memory);
        return phoneRepository.findAll(spec,pageable);
    }

    @Override
    public Phone getPhone(Long id) {
        return phoneRepository.getById(id);
    }

    @Override
    public Phone addPhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Phone savePhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public void deletePhone(Phone phone) {
        phoneRepository.delete(phone);
    }

    @Override
    public List<Phone> findByPhone(PhoneDetails phoneDetails) {
        return phoneRepository.findByPhone(phoneDetails);
    }

    @Override
    public List<MemoryEntity> getAllMemories() {
        return memoryRepository.findAll();
    }

    @Override
    public MemoryEntity getMemory(Long id) {
        return memoryRepository.getById(id);
    }

    @Override
    public MemoryEntity addMemory(MemoryEntity memory) {
        return memoryRepository.save(memory);
    }

    @Override
    public MemoryEntity saveMemory(MemoryEntity memory) {
        return memoryRepository.save(memory);
    }

    @Override
    public void deleteMemory(MemoryEntity memory) {
        memoryRepository.delete(memory);
    }

    @Override
    public List<ImageEntity> getAllImages() {
        return imageRepositroy.findAll();
    }

    @Override
    public ImageEntity getImage(Long id) {
        return imageRepositroy.getById(id);
    }

    @Override
    public ImageEntity addImage(ImageEntity image) {
        return imageRepositroy.save(image);
    }

    @Override
    public ImageEntity saveImage(ImageEntity image) {
        return imageRepositroy.save(image);
    }

    @Override
    public void deleteImage(ImageEntity image) {
        imageRepositroy.delete(image);
    }
}
