package com.example.demo.form;

import lombok.Data;

@Data
public class OrderForm {
	private Integer id;
    private Integer usersId;
    private Integer storesId;
    private Integer goodsId;  
    private Integer quantity;
}