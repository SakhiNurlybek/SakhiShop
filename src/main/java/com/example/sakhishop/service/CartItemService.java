package com.example.sakhishop.service;

import com.example.sakhishop.entity.CartItem;
import com.example.sakhishop.entity.Users;

import java.util.List;

public interface CartItemService {

    List<CartItem> getAllCartItems(Users user);

    Integer addToCart(Long phoneId, Integer quantity, Users user);
}
