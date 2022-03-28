package com.example.sakhishop.repository.phone;

import com.example.sakhishop.entity.phone.Phone;
import com.example.sakhishop.entity.phone.PhoneDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PhoneRepository extends JpaRepository<Phone,Long> {

    List<Phone> findByPhone(PhoneDetails phoneDetails);

    @Query("SELECT p FROM Phone p WHERE CONCAT(p.phone.name,' ',p.phone.brand.name,' ',p.color.name,' ',p.price) LIKE %?1%")
    Page<Phone> findAllPhones(String keyword, Pageable pageable);

    Page<Phone> findAll(Specification<Phone> spec,Pageable pageable);

}
