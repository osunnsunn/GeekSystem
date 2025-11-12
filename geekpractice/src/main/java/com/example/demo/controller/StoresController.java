package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Stores;
import com.example.demo.service.StoresService;

@Controller
public class StoresController {

	@Autowired
	private StoresService storesService;

	@GetMapping("/stores/storesControl") //店舗管理画面
	public String stores() {
		return "stores/storesControl";
	}

	@GetMapping("/stores/storesList") //店舗一覧画面
	public String showStoresList(Model model) {
		List<Stores> storesList = storesService.getAllStores();
		model.addAttribute("storesList", storesList);
		return "stores/storesList";
	}

}
