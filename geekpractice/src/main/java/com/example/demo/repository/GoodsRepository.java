package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
	
	List<Goods> findBySmallCategoryId(Integer smallCategoryId); //カテゴリID検索
	
	List<Goods> findByNameContaining(String name); //商品名で検索
	
	List<Goods> findBySmallCategoryIdAndNameContaining(Integer smallCategoryId, String name); //カテゴリIDと商品IDで検索

}
