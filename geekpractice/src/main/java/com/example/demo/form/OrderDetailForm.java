package com.example.demo.form;

import lombok.Data;

@Data
public class OrderDetailForm {
	
	private Integer id;
    private Integer ordersId;
    private Integer goodsId;
    private Integer quantity;

}
