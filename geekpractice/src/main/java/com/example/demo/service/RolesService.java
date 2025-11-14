package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Permissions;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Stores;
import com.example.demo.entity.Users;
import com.example.demo.form.UsersForm;
import com.example.demo.repository.PermissionsRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.StoresRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class RolesService {

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private PermissionsRepository permissionsRepository;

	@Autowired
	private StoresRepository storesRepository;

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Roles> getAllRoles() {
		return rolesRepository.findAll();
	}

	public List<Permissions> getAllPermissions() {
		return permissionsRepository.findAll();
	}

	public List<Stores> getAllStores() {
		return storesRepository.findAll();
	}

	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	public Users createUser(UsersForm form) {
		Users users = new Users();
		users.setRolesId(form.getRolesId());
		users.setStoresId(form.getStoresId());
		users.setFirstName(form.getFirstName());
		users.setLastName(form.getLastName());
		users.setAge(form.getAge());
		users.setEmail(form.getEmail());
		users.setPhone(form.getPhone());
		
		String hashedPassword = passwordEncoder.encode(form.getPassword());
		users.setPassword(hashedPassword);
		users.setCreatedAt(LocalDateTime.now());
		users.setUpdatedAt(LocalDateTime.now());

		return usersRepository.save(users);
	}
	
	public Users getUserById(Integer id) {
        return usersRepository.findById(id).orElse(null);
    }
	
	public Users updateUsers(Integer id, Users updatedUsers) {
        Optional<Users> opt = usersRepository.findById(id);
        if (opt.isEmpty()) return null;

        Users users = opt.get();
        users.setRolesId(updatedUsers.getRolesId());
        users.setStoresId(updatedUsers.getStoresId());
        users.setFirstName(updatedUsers.getFirstName());
        users.setLastName(updatedUsers.getLastName());
        users.setAge(updatedUsers.getAge());
        users.setEmail(updatedUsers.getEmail());
        users.setPhone(updatedUsers.getPhone());

        if (updatedUsers.getPassword() != null && !updatedUsers.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(updatedUsers.getPassword());
            users.setPassword(hashedPassword);
        }

        return usersRepository.save(users);
    }
}