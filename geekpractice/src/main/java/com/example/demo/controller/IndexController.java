package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/login")
	public String showLoginPage(HttpSession session, Model model) {
		
		System.out.println("ログイン画面");
		String errorMessage = (String) session.getAttribute("errorMessage");
		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			session.removeAttribute("errorMessage");
			System.out.println("ログインエラー");
		}
		return "login";
	}
}