package com.example.demo.data;

import lombok.Data;

@Data
public class GoodsData {

	private Integer id;
	private Integer makersId;
	private Integer smallCategoryId;
	private String name;
	private String description;
	private String costPrice;
	private String retailPrice;
	private String salesPrice;

}
