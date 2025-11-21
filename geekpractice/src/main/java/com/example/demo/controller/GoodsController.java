package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.form.GoodsSearchForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.GoodsServiceImpl;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsServiceImpl goodsService;
	
	@Autowired
    private CategoryService categoryService;

	@GetMapping("/goods/goodsControl") //商品管理 画面
	public String goodsControl() {
		return "/goods/goodsControl";
	}
	
	@GetMapping("/goods/goodsList") //商品一覧 画面
	public String goodsList(Model model) {
		
		model.addAttribute("goodsList", goodsService.findAll());
	    model.addAttribute("smallCategoryList", categoryService.findAllSmall());
	    model.addAttribute("goodsSearchForm", new GoodsSearchForm());

	    return "goods/goodsList";
	}
	
	@GetMapping("/goods/search") //商品一覧 検索
	public String searchGoods(@ModelAttribute GoodsSearchForm form, Model model) {

	    model.addAttribute("goodsList", goodsService.search(form));
	    model.addAttribute("smallCategoryList", categoryService.findAllSmall());
	    model.addAttribute("goodsSearchForm", form);

	    return "goods/goodsList";
	}
	
	@GetMapping("/goods/goodsDetail") //商品詳細 画面
	public String goodsDetail() {
		return "/goods/goodsDetail";
	}
	
	@GetMapping("/goods/goodsEdit") //商品編集 画面
	public String goodsEdit() {
		return "/goods/goodsEdit";
	}
	
	@GetMapping("/goods/goodsOrder") //商品発注 画面
	public String goodsOrder() {
		return "/goods/goodsOrder";
	}
	
	@GetMapping("/goods/goodsHistory") //発注履歴 画面
	public String goodsHistory() {
		return "/goods/goodsHistory";
	}
	
	@GetMapping("/goods/goodsStock") //商品在庫 画面
	public String goodsStock() {
		return "/goods/goodsStock";
	}
	
	@GetMapping("/goods/goodsCreate") //商品作成 画面
	public String goodsCreate() {
		return "/goods/goodsCreate";
	}

}
