package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StoresForm {
	
	@NotBlank(message = "店舗名を入力してください")
	private String name;
	
	@NotBlank(message = "住所を入力してください")
	private String address;

}
