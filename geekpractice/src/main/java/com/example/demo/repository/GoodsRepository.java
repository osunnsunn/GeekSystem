package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
	
	List<Goods> findByDeletedFalse(); 
	
	List<Goods> findByDeletedFalseAndSmallCategoryId(Integer smallCategoryId); //カテゴリID検索
	
	List<Goods> findByDeletedFalseAndNameContaining(String name); //商品名で検索
	
	List<Goods> findByDeletedFalseAndSmallCategoryIdAndNameContaining(Integer smallCategoryId, String name); //カテゴリIDと商品IDで検索
	
	Page<Goods> findByDeletedFalse(Pageable pageable);
	
	Page<Goods> findByDeletedFalseAndSmallCategoryId(Integer smallCategoryId, Pageable pageable);

    Page<Goods> findByDeletedFalseAndNameContaining(String name, Pageable pageable);

    Page<Goods> findByDeletedFalseAndSmallCategoryIdAndNameContaining(Integer smallCategoryId, String name, Pageable pageable);
}
