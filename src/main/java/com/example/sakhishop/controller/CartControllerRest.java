package com.example.sakhishop.controller;

import com.example.sakhishop.entity.Users;
import com.example.sakhishop.service.CartItemService;
import com.example.sakhishop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartControllerRest {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/cart/add/{pid}/{qty}")
    @PreAuthorize("isAuthenticated()")
    public String addItemtoCart(
            @PathVariable(name = "pid")Long phoneId,
            @PathVariable(name = "qty")Integer quantity){

        System.out.println("addProductTocart:" + phoneId + "-"+ quantity);
        Users user = getUserData();
        if(user==null){
            return "You need to Authenticated";
        }
        Integer addedQuantity = cartItemService.addToCart(phoneId,quantity,user);
        return addedQuantity + "item(s) added to your shopping cart";
    }


    private Users getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User secUser = (User) authentication.getPrincipal();
            Users myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }
}
