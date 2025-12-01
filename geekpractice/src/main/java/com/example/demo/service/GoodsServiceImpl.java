package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Goods;
import com.example.demo.form.GoodsForm;
import com.example.demo.form.GoodsSearchForm;
import com.example.demo.repository.GoodsRepository;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
    private GoodsRepository goodsRepository;
	
	@Autowired
	private ImageStorageService imageStorageService;

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findByDeletedFalse();
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
            return goodsRepository.findByDeletedFalseAndSmallCategoryIdAndNameContaining(categoryId, goodsName);
        } else if (categoryId != null && categoryId != 0) {
            return goodsRepository.findByDeletedFalseAndSmallCategoryId(categoryId);
        } else {
            return goodsRepository.findByDeletedFalseAndNameContaining(goodsName);
        }
    }
    
    @Override
    public Page<Goods> search(GoodsSearchForm form, Pageable pageable) {

        Integer categoryId = form.getSmallCategoryId();
        String goodsName = form.getGoodsName();

        if ((categoryId == null || categoryId == 0) && (goodsName == null || goodsName.isEmpty())) {
            return goodsRepository.findAll(pageable);
        }
        if (categoryId != null && categoryId != 0 && goodsName != null && !goodsName.isEmpty()) {
            return goodsRepository.findByDeletedFalseAndSmallCategoryIdAndNameContaining(categoryId, goodsName, pageable);
        }
        if (categoryId != null && categoryId != 0) {
            return goodsRepository.findByDeletedFalseAndSmallCategoryId(categoryId, pageable);
        }

        return goodsRepository.findByDeletedFalseAndNameContaining(goodsName, pageable);
    }
    
    public Goods findById(Integer id) {
        return goodsRepository.findById(id).orElse(null);
    }

    @Override
    public void update(GoodsForm form) {
        Goods goods = goodsRepository.findById(form.getId()).orElse(null);
        if (goods == null) return;

        goods.setName(form.getName());
        goods.setDescription(form.getDescription());
        goods.setSmallCategoryId(form.getSmallCategoryId());

        // makersId が null にならないよう必ずセット
        if (form.getMakersId() != null) {
            goods.setMakersId(form.getMakersId());
        }

        goods.setCostPrice(form.getCostPrice());
        goods.setRetailPrice(form.getRetailPrice());
        goods.setSalesPrice(form.getSalesPrice());

        if (form.getImage() != null && !form.getImage().isEmpty()) {
            String fileName = imageStorageService.storeImage(form.getImage());
            goods.setImage(fileName);
        }

        goodsRepository.save(goods);
    }

    @Override
    public void delete(Integer id) {
        Goods goods = goodsRepository.findById(id).orElse(null);
        if (goods != null) {
            goods.setDeleted(true);
            goodsRepository.save(goods);
        }
    }
    
    @Override
    public void create(GoodsForm form) {

    	String fileName = imageStorageService.storeImage(form.getImage());

        Goods goods = new Goods();
        goods.setName(form.getName());
        goods.setDescription(form.getDescription());
        goods.setSmallCategoryId(form.getSmallCategoryId());
        goods.setMakersId(form.getMakersId());
        goods.setCostPrice(form.getCostPrice());
        goods.setRetailPrice(form.getRetailPrice());
        goods.setSalesPrice(form.getSalesPrice());
        goods.setImage(fileName);

        goodsRepository.save(goods);
    }

}