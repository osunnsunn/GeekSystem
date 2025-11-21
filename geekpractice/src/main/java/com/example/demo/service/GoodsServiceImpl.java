package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Goods;
import com.example.demo.form.GoodsSearchForm;
import com.example.demo.repository.GoodsRepository;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Autowired
    private GoodsRepository goodsRepository;

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public List<Goods> search(GoodsSearchForm form) {

        Integer categoryId = form.getSmallCategoryId();
        String goodsName = form.getGoodsName();

        if ((categoryId == null || categoryId == 0) &&
            (goodsName == null || goodsName.isEmpty())) {
            return goodsRepository.findAll();
        }

        if (categoryId != null && categoryId != 0 && goodsName != null && !goodsName.isEmpty()) {
            return goodsRepository.findBySmallCategoryIdAndNameContaining(categoryId, goodsName);
        } else if (categoryId != null && categoryId != 0) {
            return goodsRepository.findBySmallCategoryId(categoryId);
        } else {
            return goodsRepository.findByNameContaining(goodsName);
        }
    }

}