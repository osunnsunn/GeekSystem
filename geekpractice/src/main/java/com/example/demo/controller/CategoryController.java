package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.LargeCategory;
import com.example.demo.entity.MiddleCategory;
import com.example.demo.entity.SmallCategory;
import com.example.demo.service.CategoryService;



@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/category/largeCategoryList")
	public String showAdminList(Model model) {
		List<LargeCategory> largeCategory = categoryService.getAllLargeCategory();
		model.addAttribute("largeCategoryList", largeCategory);
		return "category/largeCategoryList";
	}
	
	@GetMapping("/category/middleCategoryList/{id}")
	public String showMiddleCategoryDetail(@PathVariable Integer id, Model model) {
		
		List<MiddleCategory> middleCategory = categoryService.getMiddleCategoryById(id);
		model.addAttribute("middleCategoryList", middleCategory);
		model.addAttribute("largeCategoryId", id);
		return "/category/middleCategoryList";
	}
	
	@GetMapping("/category/smallCategoryList/{id}")
	public String showSmallCategoryDetail(@PathVariable Integer id, Model model) {
		
		List<SmallCategory> smallCategory = categoryService.getSmallCategoryById(id);
		model.addAttribute("smallCategoryList", smallCategory);
		model.addAttribute("middleCategoryId", id);
		return "/category/smallCategoryList";
	}

}