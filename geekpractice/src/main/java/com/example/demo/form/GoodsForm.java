package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class GoodsForm {
	
	@NotNull(message = " メーカーIDがありません")
	private Integer makersId;

	@NotNull(message = "小カテゴリがありません")
	private Integer smallCategoryId;

	@NotBlank(message = "商品名がありません")
	private String name;

	@NotBlank(message = "説明がありません")
	private String description;
	
	@NotNull(message = "仕入れ原価がありません")
	private Integer costPrice;

	@NotBlank(message = "メーカ希望小売価格がありません")
	private String retailPrice;

	@NotBlank(message = "販売価格がありません")
	private String salesPrice;
	
	private MultipartFile image;

}