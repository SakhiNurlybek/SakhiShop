package com.example.sakhishop.repository;

import com.example.sakhishop.entity.CartItem;
import com.example.sakhishop.entity.Users;
import com.example.sakhishop.entity.phone.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void addOnePhone(){

        Phone phone = entityManager.find(Phone.class,55);
        Users user = entityManager.find(Users.class,3);

        CartItem cartItem = new CartItem();
        cartItem.setPhone(phone);
        cartItem.setUser(user);
        cartItem.setQuantity(1);

        CartItem savedItem = cartItemRepository.save(cartItem);
        assertTrue(savedItem.getId() > 0);

    }

}