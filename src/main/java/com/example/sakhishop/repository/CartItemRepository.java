package com.example.sakhishop.repository;

import com.example.sakhishop.entity.CartItem;
import com.example.sakhishop.entity.Users;
import com.example.sakhishop.entity.phone.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByUser(Users users);

    CartItem findByUserAndPhone(Users user, Phone phone);
}
