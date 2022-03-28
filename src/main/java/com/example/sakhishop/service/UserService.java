package com.example.sakhishop.service;

import com.example.sakhishop.entity.Roles;
import com.example.sakhishop.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    Users getUserByEmail(String email);

    Users createUser(Users user);

    Users getUser(Long id);

    Users saveUser(Users user);

    List<Users> getAllUsers();

    List<Roles> getAllRoles();

    Roles getRole(Long id);

}
