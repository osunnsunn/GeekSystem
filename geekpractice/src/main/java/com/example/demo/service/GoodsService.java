package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Goods;
import com.example.demo.form.GoodsForm;
import com.example.demo.form.GoodsSearchForm;

public interface GoodsService {
	List<Goods> findAll();
    List<Goods> search(GoodsSearchForm form);
    Goods findById(Integer id);
    void update(GoodsForm form);
    void delete(Integer id);
    void create(GoodsForm form);
}