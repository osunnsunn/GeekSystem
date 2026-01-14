package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;

@Service
public class AuthService {
    public boolean isAdmin(Users user) {
    	return user.getRoles().getPermissionsId() == 1;
    }
}