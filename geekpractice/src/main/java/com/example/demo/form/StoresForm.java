package com.example.demo.form;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StoresForm {
	
	@NotNull(message = "店舗名を入力してください")
	private String name;
	
	@NotNull(message = "住所を入力してください")
	private String address;

}
