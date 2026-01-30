package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.UsersData;
import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

@Service
public class UsersApiService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public List<UsersApiData> getUsersList() {
        return usersRepository.findAll()
                .stream()
                .map(u -> new UsersApiData(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getEmail()
                ))
                .toList();
    }

	public UsersData getUserById(Integer id) {
	    Users user = usersRepository.findById(id).orElseThrow();
	    UsersData data = new UsersData();
	    data.setId(user.getId());
	    data.setFirstName(user.getFirstName());
	    data.setLastName(user.getLastName());
	    data.setAge(user.getAge());
	    data.setEmail(user.getEmail());
	    data.setPhone(user.getPhone());

	    return data;
	}

}