package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MakersForm {
	
	@NotBlank(message = "メーカー名を入力してください")
	private String name;

}
