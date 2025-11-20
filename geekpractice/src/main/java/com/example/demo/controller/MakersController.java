package com.example.demo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Makers;
import com.example.demo.form.MakersForm;
import com.example.demo.service.MakersService;

@Controller
public class MakersController {
	
	@Autowired
	private MakersService makersService;
	
	@GetMapping("/makers/makersControl") //メーカー管理画面
	public String makers() {
		return "makers/makersControl";
	}
	
	@GetMapping("/makers/makersList") //メーカー一覧画面
	public String showStoresList(Model model) {
		List<Makers> makersList = makersService.getAllMakers();
		model.addAttribute("makersList", makersList);
		return "makers/makersList";
	}
	
	@GetMapping("/makers/makersCreate") //メーカー作成画面
	public String showMakersCreateForm(Model model) {
		model.addAttribute("malersList", makersService.getAllMakers());
		model.addAttribute("makersForm", new MakersForm());
		return "makers/makersCreate";
	}
	
	@PostMapping("/makers/makersCreate") //メーカー作成
	public String createMakers(@Valid @ModelAttribute("makersForm") MakersForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("makersList", makersService.getAllMakers());
			model.addAttribute("errorMessage", "登録に失敗しました");
			return "makers/makersCreate";
		}
		makersService.createMakers(form);
		model.addAttribute("successMessage", "登録完了しました");
		return "makers/makersCreate";
	}

}
