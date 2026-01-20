package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Stores;
import com.example.demo.entity.Users;
import com.example.demo.form.GoodsForm;
import com.example.demo.form.GoodsSearchForm;
import com.example.demo.form.OrderForm;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.CategoryService;
import com.example.demo.service.GoodsServiceImpl;
import com.example.demo.service.MakersService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.StoreStockService;
import com.example.demo.service.StoresService;
import com.example.demo.util.ExcelTest;

@Controller
public class GoodsController {

	@Autowired
	private GoodsServiceImpl goodsService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MakersService makersService;

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private StoresService storesService;
	
	@Autowired
	private StoreStockService storeStockService;
	
	@Autowired
	private ExcelTest excelTest;

	@GetMapping("/goods/goodsControl") //商品管理 画面
	public String goodsControl() {
		return "/goods/goodsControl";
	}

	@GetMapping("/goods/goodsList") //商品一覧 画面
	public String goodsList(@RequestParam(defaultValue = "0") int page, Model model) {
		
		GoodsSearchForm form = new GoodsSearchForm();
		Pageable pageable = PageRequest.of(page, 10);
	    Page<Goods> goodsPage = goodsService.search(form, pageable);
		
		model.addAttribute("goodsList", goodsPage.getContent());
	    model.addAttribute("goodsPage", goodsPage);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", goodsPage.getTotalPages());
	    model.addAttribute("smallCategoryList", categoryService.findAllSmall());
	    model.addAttribute("goodsSearchForm", form);

		return "goods/goodsList";
	}

	@GetMapping("/goods/search") //商品一覧 検索
	
	public String searchGoods(@ModelAttribute GoodsSearchForm form, @RequestParam(defaultValue = "0") int page, Model model) {

	    Pageable pageable = PageRequest.of(page, 10);
	    Page<Goods> goodsPage = goodsService.search(form, pageable);

	    model.addAttribute("goodsList", goodsPage.getContent());
	    model.addAttribute("goodsPage", goodsPage);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", goodsPage.getTotalPages());
	    model.addAttribute("smallCategoryList", categoryService.findAllSmall());
	    model.addAttribute("goodsSearchForm", form);

		return "goods/goodsList";
	}

	@GetMapping("/goods/goodsDetail/{id}") //商品詳細 画面
	public String goodsDetail(@PathVariable Integer id, Model model) {
		
		Goods goods = goodsService.findById(id);
		model.addAttribute("goods", goods);
		return "goods/goodsDetail";
	}

	@GetMapping("/goods/goods/{id}/Edit") //商品編集 画面
	public String goodsEdit(@PathVariable Integer id, Model model) {
		
		Goods goods = goodsService.findById(id);
		GoodsForm form = new GoodsForm();
		form.setId(goods.getId());
	    form.setName(goods.getName());
	    form.setDescription(goods.getDescription());
	    form.setSmallCategoryId(goods.getSmallCategoryId());
	    form.setMakersId(goods.getMakersId());
	    form.setCostPrice(goods.getCostPrice());
	    form.setRetailPrice(goods.getRetailPrice());
	    form.setSalesPrice(goods.getSalesPrice());
		
		model.addAttribute("goodsForm", form);
		model.addAttribute("goods", goods);
		model.addAttribute("smallCategoryList", categoryService.findAllSmall());
		model.addAttribute("makersList", makersService.getAllMakers());
		return "goods/goodsEdit";
	}

	@PostMapping("/goods/goods/{id}/Edit") //編集処理
		public String editGoods(@PathVariable Integer id, @Valid @ModelAttribute("goodsForm") GoodsForm form, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("goods", goodsService.findById(id));
	        model.addAttribute("smallCategoryList", categoryService.findAllSmall());
	        model.addAttribute("makersList", makersService.getAllMakers());
	        return "goods/goodsEdit";
	    }
	    form.setId(id);
	    goodsService.update(form);
	    model.addAttribute("goods", goodsService.findById(id));
	    model.addAttribute("smallCategoryList", categoryService.findAllSmall());
	    model.addAttribute("makersList", makersService.getAllMakers());
	    model.addAttribute("successMessage", "商品情報を更新しました！");
			
		return "goods/goodsEdit";
	}

	@PostMapping("/goods/goodsDetail/{id}") //削除処理
	public String deleteGoods(@PathVariable Integer id, @RequestParam(defaultValue = "0") int page, Model model) {
		
		goodsService.delete(id);
		
		GoodsSearchForm form = new GoodsSearchForm();
		Pageable pageable = PageRequest.of(page, 10);
	    Page<Goods> goodsPage = goodsService.search(form, pageable);
		
		model.addAttribute("goodsList", goodsPage.getContent());
	    model.addAttribute("goodsPage", goodsPage);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", goodsPage.getTotalPages());
	    model.addAttribute("smallCategoryList", categoryService.findAllSmall());
	    model.addAttribute("goodsSearchForm", form);
		
		model.addAttribute("successMessage", "商品を削除しました。");
		return "goods/goodsList";
	}

	@GetMapping("/goods/goodsOrder") //商品発注 画面
	public String goodsOrder(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {

		if (userDetails == null) {
			return "redirect:/login";
		}

		Users loginUser = userDetails.getUser();

		model.addAttribute("storesId", loginUser.getStoresId());
		model.addAttribute("storesName",Optional.ofNullable(storesService.getStoresById(loginUser.getStoresId())).map(Stores::getName).orElse(""));
		model.addAttribute("usersId", loginUser.getId());
		model.addAttribute("userName", loginUser.getLastName() + " " + loginUser.getFirstName());
		model.addAttribute("goodsList", goodsService.findAll());
		model.addAttribute("OrderForm", new OrderForm());
		return "goods/goodsOrder";
	}

	@PostMapping("/goods/goodsOrder") //発注処理
	public String submitOrder(@ModelAttribute OrderForm form, Model model,
			@AuthenticationPrincipal CustomUserDetails userDetails) {

		if (userDetails != null) {
			Users loginUser = userDetails.getUser();
			form.setUsersId(loginUser.getId());
			form.setStoresId(loginUser.getStoresId());
		}

		ordersService.createOrder(form);

		model.addAttribute("OrderForm", form);
		model.addAttribute("storesName", Optional.ofNullable(storesService.getStoresById(form.getStoresId())).map(Stores::getName).orElse("未設定"));
		model.addAttribute("userName",
				userDetails != null ? userDetails.getUser().getLastName() + " " + userDetails.getUser().getFirstName()
						: "未設定");
		model.addAttribute("goodsList", goodsService.findAll());
		model.addAttribute("successMessage", "発注が完了しました！");

		return "goods/goodsOrder";
	}

	@GetMapping("/goods/goodsHistory") //発注履歴 画面
	public String goodsHistory(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {

		if (userDetails == null) {
			return "redirect:/login";
		}

		Users loginUser = userDetails.getUser();
		Integer storeId = loginUser.getStoresId();

		model.addAttribute("storesId", storeId);
		model.addAttribute("storesName", storesService.getStoresById(storeId).getName());
		model.addAttribute("usersId", loginUser.getId());
		model.addAttribute("userName", loginUser.getLastName() + " " + loginUser.getFirstName());

		List<Orders> ordersList = ordersService.getOrdersHistoryByStore(storeId);
		model.addAttribute("ordersList", ordersList);

		return "goods/goodsHistory";
	}
	
	@PostMapping("/goods/goodsHistory") //発注履歴 ファイル出力
	public String goodsHistoryXlsx(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails == null) {
			model.addAttribute("errorMessage", "作成に失敗しました");
	        return "goods/goodsHistory";
	    }
		excelTest.exportOrderDetail();
		model.addAttribute("successMessage", "ファイル作成しました！");

		Users loginUser = userDetails.getUser();
		Integer storeId = loginUser.getStoresId();

		model.addAttribute("storesId", storeId);
		model.addAttribute("storesName", storesService.getStoresById(storeId).getName());
		model.addAttribute("usersId", loginUser.getId());
		model.addAttribute("userName", loginUser.getLastName() + " " + loginUser.getFirstName());

		List<Orders> ordersList = ordersService.getOrdersHistoryByStore(storeId);
		model.addAttribute("ordersList", ordersList);
		return "goods/goodsHistory";
	}
	

	@GetMapping("/goods/goodsStock") //商品在庫 画面
	public String showGoodsStock(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
	    Integer storeId = userDetails.getStoreId();
	    List<StoreStockService.GoodsStock> goodsStockList = storeStockService.getGoodsStockByStore(storeId);

	    model.addAttribute("goodsStockList", goodsStockList);
	    return "goods/goodsStock";
	}

	@GetMapping("/goods/goodsCreate") //商品作成 画面
	public String showCreateForm(Model model) {

		model.addAttribute("goodsForm", new GoodsForm());

		model.addAttribute("smallCategoryList", categoryService.findAllSmall());
		model.addAttribute("makersList", makersService.getAllMakers());

		return "goods/goodsCreate";
	}

	@PostMapping("/goods/goodsCreate") //作成処理
	public String createGoods(@ModelAttribute("goodsForm") @Valid GoodsForm form,
			BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("smallCategoryList", categoryService.findAllSmall());
			model.addAttribute("makersList", makersService.getAllMakers());
			return "goods/goodsCreate";
		}

		try {
			goodsService.create(form);
		} catch (RuntimeException e) {
			model.addAttribute("errorMessage", "商品作成に失敗しました");
			model.addAttribute("smallCategoryList", categoryService.findAllSmall());
			model.addAttribute("makersList", makersService.getAllMakers());
			return "goods/goodsCreate";
		}
		model.addAttribute("successMessage", "商品作成しました！");
		return "/goods/goodsCreate";
	}

}