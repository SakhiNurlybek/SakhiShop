package com.example.sakhishop.controller;

import com.example.sakhishop.entity.CartItem;
import com.example.sakhishop.entity.Users;
import com.example.sakhishop.service.CartItemService;
import com.example.sakhishop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String cartItems(Model model){

        Users currentUser = getUserData();

        List<CartItem> cartItems = cartItemService.getAllCartItems(currentUser);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("cartItems",cartItems);

        return "cart/cart";
    }

    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            Users myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }
}
