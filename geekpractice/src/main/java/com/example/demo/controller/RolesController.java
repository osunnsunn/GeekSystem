package com.example.demo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Users;
import com.example.demo.form.UsersForm;
import com.example.demo.service.RolesService;

@Controller
public class RolesController {

	@Autowired
	private RolesService rolesService;

	@GetMapping("/admin/rolesControl") //管理者管理画面
	public String roles() {
		return "admin/rolesControl";
	}

	@GetMapping("/admin/rolesCreate") //管理者作成画面
	public String showRolesCreateForm(Model model) {
		model.addAttribute("rolesList", rolesService.getAllRoles());
		model.addAttribute("permissionsList", rolesService.getAllPermissions());
		model.addAttribute("storesList", rolesService.getAllStores());
		model.addAttribute("usersList", rolesService.getAllUsers());
		model.addAttribute("usersForm", new UsersForm());
		return "admin/rolesCreate";
	}

	@PostMapping("/admin/rolesCreate") //管理者作成
	public String createUsers(@Valid @ModelAttribute("usersForm") UsersForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("rolesList", rolesService.getAllRoles());
			model.addAttribute("permissionsList", rolesService.getAllPermissions());
			model.addAttribute("storesList", rolesService.getAllStores());
			model.addAttribute("usersList", rolesService.getAllUsers());
			model.addAttribute("errorMessage", "登録失敗しました");
			return "admin/rolesCreate";
		}

		rolesService.createUser(form);
		model.addAttribute("successMessage", "登録完了しました");
		return "admin/rolesCreate";
	}

	@GetMapping("/admin/rolesList") //管理者一覧画面
	public String showAdminList(Model model) {
		List<Users> usersList = rolesService.getAllUsers();
		model.addAttribute("usersList", usersList);
		return "admin/rolesList";
	}

	@GetMapping("/admin/roles/{id}") //管理者詳細画面
	public String showUsersDetail(@PathVariable Integer id, Model model) {

		Users users = rolesService.getUserById(id);
		if (users == null) {
			model.addAttribute("errorMessage", "該当ユーザーが見つかりません");
			return "admin/rolesList";
		}
		model.addAttribute("users", users);
		return "admin/rolesDetail";
	}

	@GetMapping("/admin/roles/{id}/Edit") //管理者編集画面
	public String showEditForm(@PathVariable Integer id, Model model) {
	    Users users = rolesService.getUserById(id);
	    if (users == null) {
	        model.addAttribute("errorMessage", "該当ユーザーが見つかりません");
	        return "admin/rolesList";
	    }
	    model.addAttribute("users", users);
	    model.addAttribute("rolesList", rolesService.getAllRoles());
	    model.addAttribute("permissionsList", rolesService.getAllPermissions());
	    model.addAttribute("storesList", rolesService.getAllStores());
	    return "admin/rolesEdit";
	}

	@PostMapping("/admin/roles/{id}/Edit") //管理者編集
	public String updateUsers(@PathVariable Integer id, @Valid @ModelAttribute("users") Users users, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("users", rolesService.getUserById(id));
			model.addAttribute("rolesList", rolesService.getAllRoles());
			model.addAttribute("permissionsList", rolesService.getAllPermissions());
			model.addAttribute("storesList", rolesService.getAllStores());
			model.addAttribute("errorMessage", "更新失敗しました");
			return "admin/rolesEdit";
		}

		rolesService.updateUsers(id, users);
		model.addAttribute("successMessage", "更新が完了しました");
		return "admin/rolesEdit";
	}
}