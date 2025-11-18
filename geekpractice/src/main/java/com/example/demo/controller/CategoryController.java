package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
	public String showAdminList(Model madel) {
		List<LargeCategory> largeCategoryList = categoryService.getAllLargeCategory();
		model.addAttribute("largeCategoryList", largeCategoryList);
		return "category/largeCategoryList";
	}
	
	@GetMapping("/category/middleCategoryList/{id}")
	public String showMiddleCategoryDetail(@PathVariable Integer id, Model model) {
		
		MiddleCategory middleCategory = categoryService.getAllMiddleCategory(id);
		model.addAttribute("middleCategory", middleCategory);
		return "/category/middleCategoryList/{id}";
	}
	
	@GetMapping("/category/smallCategoryList/{id}")
	public String showSmallCategoryDetail(@PathVariable Integer id, Model model) {
		
		SmallCategory smallCategory = categoryService.getAllSmallCategory(id);
		model.addAttribute("smallCategory", smallCategory);
		return "/category/smallCategoryList/{id}";
	}


}
