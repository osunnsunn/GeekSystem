package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.security.CustomUserDetails;

@Controller
public class IndexController {

	@GetMapping("/home")
	public String showHome(Model model, @AuthenticationPrincipal CustomUserDetails loginUser) {

		if (loginUser != null) {
	        Users currentUser = loginUser.getUser();
	        model.addAttribute("currentUser", currentUser);
	        model.addAttribute("roleName", currentUser.getRoles().getName());
	    }

	    return "home";
	}

	@GetMapping("/login")
    public String showLoginPage(
            HttpSession session, 
            Model model,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout) {

        if (error != null) {
            model.addAttribute("errorMessage", "ログインに失敗しました");
        }

        if (logout != null) {
            model.addAttribute("logoutMessage", "ログアウトしました");
        }

        String sessionError = (String) session.getAttribute("errorMessage");
        if (sessionError != null) {
            session.removeAttribute("errorMessage");
        }

        return "login";
    }
}