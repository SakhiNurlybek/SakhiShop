package com.example.sakhishop.repository.phone;

import com.example.sakhishop.entity.phone.MemoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MemoryRepository extends JpaRepository<MemoryEntity,Long> {

}
