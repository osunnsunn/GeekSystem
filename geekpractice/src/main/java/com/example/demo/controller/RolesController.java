package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Permissions;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Stores;
import com.example.demo.entity.Users;
import com.example.demo.form.UsersForm;
import com.example.demo.repository.PermissionsRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.StoresRepository;
import com.example.demo.repository.UsersRepository;

@Controller
public class RolesController {

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

	@GetMapping("/admin/rolesControl")
	public String roles() {
		return "admin/rolesControl";
	}

	@GetMapping("/admin/rolesCreate")
	public String showRolesCreateForm(Model model) {

		List<Roles> rolesList = rolesRepository.findAll();
		List<Permissions> permissionsList = permissionsRepository.findAll();
		List<Stores> storesList = storesRepository.findAll();
		List<Users> usersList = usersRepository.findAll();

		model.addAttribute("rolesList", rolesList);
		model.addAttribute("permissionsList", permissionsList);
		model.addAttribute("storesList", storesList);
		model.addAttribute("usersList", usersList);

		model.addAttribute("usersForm", new UsersForm());

		return "admin/rolesCreate";
	}

	@PostMapping("/admin/rolesCreate")
	public String createUsers(@Valid @ModelAttribute("usersForm") UsersForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("rolesList", rolesRepository.findAll());
			model.addAttribute("permissionsList", permissionsRepository.findAll());
			model.addAttribute("storesList", storesRepository.findAll());
			model.addAttribute("usersList", usersRepository.findAll());
			model.addAttribute("errorMessage", "登録失敗しました");
			return "admin/rolesCreate";
		}
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

		usersRepository.save(users);
		model.addAttribute("successMessage", "登録完了しました");
		return "admin/rolesCreate";
	}
	
	@GetMapping("/admin/rolesList")
	public String showAdminList(Model model) {
		List<Users> usersList = usersRepository.findAll();
		model.addAttribute("usersList", usersList);
		return "admin/rolesList";
	}
	
	@GetMapping("/admin/rolesDetail")
	public String rolesDetail() {
		return "admin/rolesDetail";
	}
	
}