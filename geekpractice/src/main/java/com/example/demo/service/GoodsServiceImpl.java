package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Goods;
import com.example.demo.form.GoodsForm;
import com.example.demo.form.GoodsSearchForm;
import com.example.demo.repository.GoodsRepository;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Autowired
    private GoodsRepository goodsRepository;
	
	@Autowired
	private ImageStorageService imageStorageService;

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
    
    public Goods findById(Integer id) {
        return goodsRepository.findById(id).orElse(null);
    }

    public void update(Goods goods) {
        goodsRepository.save(goods);
    }

    public void delete(Integer id) {
    	goodsRepository.deleteById(id);
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
        goods.setImagePath(fileName);

        goodsRepository.save(goods);
    }

}