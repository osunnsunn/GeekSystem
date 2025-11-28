package com.example.demo.service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

@Service
@Primary
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("ユーザー認証");
	    Users user = usersRepository.findByEmail(email)
	            .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + email));

	    return User.withUsername(user.getEmail())
	    		.password(user.getPassword()) 
	    		.roles("USER")
	            .build();
    }
}