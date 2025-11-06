package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Permissions;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Stores;
import com.example.demo.repository.PermissionsRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.StoresRepository;

@Controller
public class RolesController {

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private PermissionsRepository permissionsRepository;

	@Autowired
	private StoresRepository storesRepository;

	@GetMapping("/roles/control")
	public String roles() {
		return "roles/control";
	}

	@GetMapping("/roles/create")
	public String showRolesCreateForm(Model model) {

		List<Roles> rolesList = rolesRepository.findAll();
		List<Permissions> permissionsList = permissionsRepository.findAll();
		List<Stores> storesList = storesRepository.findAll();

		model.addAttribute("rolesList", rolesList);
		model.addAttribute("permissionsList", permissionsList);
		model.addAttribute("storesList", storesList);

		return "roles/create";
	}
}