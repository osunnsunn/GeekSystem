package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Goods;
import com.example.demo.form.GoodsForm;
import com.example.demo.form.GoodsSearchForm;

public interface GoodsService {
	List<Goods> findAll();
    List<Goods> search(GoodsSearchForm form);
    Page<Goods> search(GoodsSearchForm form, Pageable pageable);
    Goods findById(Integer id);
    void update(GoodsForm form);
    void delete(Integer id);
    void create(GoodsForm form);
}