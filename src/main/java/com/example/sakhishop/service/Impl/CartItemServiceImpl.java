package com.example.sakhishop.service.Impl;

import com.example.sakhishop.entity.CartItem;
import com.example.sakhishop.entity.Users;
import com.example.sakhishop.entity.phone.Phone;
import com.example.sakhishop.repository.CartItemRepository;
import com.example.sakhishop.repository.phone.PhoneRepository;
import com.example.sakhishop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public List<CartItem> getAllCartItems(Users user) {
        return cartItemRepository.findByUser(user);
    }

    @Override
    public Integer addToCart(Long phoneId, Integer quantity, Users user) {
        Integer addedQuantity = quantity;
        Phone phone = phoneRepository.getById(phoneId);
        CartItem cartItem = cartItemRepository.findByUserAndPhone(user,phone);
        if(cartItem!=null){
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        }else{
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
            cartItem.setPhone(phone);
        }
        cartItemRepository.save(cartItem);
        return addedQuantity;
    }
}
