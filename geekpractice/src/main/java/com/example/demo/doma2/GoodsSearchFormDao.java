package com.example.demo.doma2;

import lombok.Data;

@Data
public class GoodsSearchFormDao {
	
	private Integer smallCategoryId;
    private String keyword;
    private String goodsName;
    
    private Integer page = 1;
    private Integer size = 10;
    
    public int getOffset() {
        return (page - 1) * size;
    }
}
