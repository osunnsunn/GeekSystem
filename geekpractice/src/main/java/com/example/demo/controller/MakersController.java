package com.example.demo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Makers;
import com.example.demo.entity.Users;
import com.example.demo.form.MakersForm;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.AuthService;
import com.example.demo.service.MakersService;
import com.example.demo.service.RolesService;

@Controller
public class MakersController {
	
	@Autowired
	private MakersService makersService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private RolesService rolesService;
	
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
	
	@GetMapping("/makers/makers/{id}") //メーカー詳細画面
	public String showMakersDetail(@PathVariable Integer id, Model model, @AuthenticationPrincipal CustomUserDetails loginUser) {
		
		Users currentUser = rolesService.getUserById(loginUser.getUser().getId());
		if (!authService.isAdmin(currentUser)) {
		    model.addAttribute("errorMessage", "管理者権限が必要です");
		    List<Makers> makersList = makersService.getAllMakers();
			model.addAttribute("makersList", makersList);
			return "makers/makersList";
		}
		Makers makers = makersService.getMakersById(id);
		if(makers == null) {
			model.addAttribute("errorMessage", "該当店舗が見つかりません");
			return "makers/makersDetail";
		}
		model.addAttribute("makers", makers);
	    return "makers/makersDetail";
	}
	
	@GetMapping("/makers/makers/{id}/Edit") //メーカー編集画面
	public String showEditForm(@PathVariable Integer id, Model model) {
	    Makers makers = makersService.getMakersById(id);
	    
	    if (makers == null) {
	        model.addAttribute("errorMessage", "該当店舗が見つかりません");
	        return "makers/makersList";
	    }
	    
	    MakersForm form = new MakersForm();
	    form.setName(makers.getName());

	    model.addAttribute("makersForm", form);
	    model.addAttribute("makers", makers);
	    model.addAttribute("makersList", makersService.getAllMakers());
	    return "makers/makersEdit";
	}

	@PostMapping("/makers/makers/{id}/Edit") //メーカー編集
	public String updatoreMakers(@PathVariable Integer id, @Valid @ModelAttribute("makersForm") MakersForm form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			Makers makers = makersService.getMakersById(id);
			model.addAttribute("makers", makers);
			model.addAttribute("makersForm", form);
			model.addAttribute("makersList", makersService.getAllMakers());
			model.addAttribute("errorMessage", "更新失敗しました");
			return "makers/makersEdit";
		}

		makersService.updateMakers(id, form);
		model.addAttribute("makers", makersService.getMakersById(id));
		model.addAttribute("makersList", makersService.getAllMakers());
		model.addAttribute("successMessage", "更新が完了しました");
		return "makers/makersEdit";
	}
	
	@GetMapping("/makers/makersCreate") //メーカー作成画面
	public String showMakersCreateForm(Model model, @AuthenticationPrincipal CustomUserDetails loginUser) {
		
		Users currentUser = rolesService.getUserById(loginUser.getUser().getId());
		if (!authService.isAdmin(currentUser)) {
		    model.addAttribute("errorMessage", "管理者権限が必要です");
		    return "makers/makersControl";
		}
		model.addAttribute("maersList", makersService.getAllMakers());
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