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

import com.example.demo.entity.Stores;
import com.example.demo.form.StoresForm;
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
	
	@GetMapping("/stores/stores/{id}") //店舗詳細画面
	public String showStoresDetail(@PathVariable Integer id, Model model) {
		Stores stores = storesService.getStoresById(id);
		if(stores == null) {
			model.addAttribute("errorMessage", "該当店舗が見つかりません");
			return "stores/storesDetail";
		}
		model.addAttribute("stores", stores);
	    return "stores/storesDetail";
	}
	
	@GetMapping("/stores/stores/{id}/Edit") //店舗編集画面
	public String showEditForm(@PathVariable Integer id, Model model) {
	    Stores stores = storesService.getStoresById(id);
	    
	    if (stores == null) {
	        model.addAttribute("errorMessage", "該当店舗が見つかりません");
	        return "stores/storesList";
	    }
	    
	    model.addAttribute("stores", stores);
	    model.addAttribute("storesList", storesService.getAllStores());
	    return "stores/storesEdit";
	}

	@PostMapping("/stores/stores/{id}/Edit") //店舗編集
	public String updatorestores(@PathVariable Integer id, @Valid @ModelAttribute("stores") Stores stores, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("stores", storesService.getStoresById(id));
			model.addAttribute("storesList", storesService.getAllStores());
			model.addAttribute("errorMessage", "更新失敗しました");
			return "stores/storesEdit";
		}

		storesService.updateStores(id, stores);
		model.addAttribute("stores", storesService.getStoresById(id));
		model.addAttribute("storesList", storesService.getAllStores());
		model.addAttribute("successMessage", "更新が完了しました");
		return "stores/storesEdit";
	}
	
	@GetMapping("/stores/storesCreate") //店舗作成画面
	public String showStoresCreateForm(Model model) {
		model.addAttribute("storesList", storesService.getAllStores());
		model.addAttribute("storesForm", new StoresForm());
		return "stores/storesCreate";
	}

	@PostMapping("/stores/storesCreate") //店舗作成
	public String createStores(@Valid @ModelAttribute("storesForm") StoresForm form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
	        model.addAttribute("storesList", storesService.getAllStores());
	        model.addAttribute("errorMessage", "登録失敗しました");
	        return "stores/storesCreate";
	    }

		storesService.createStores(form);
		model.addAttribute("successMessage", "登録完了しました");
		return "stores/storesCreate";
	}

}
