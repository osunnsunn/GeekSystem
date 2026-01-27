package com.example.demo.doma2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Goods;
import com.example.demo.form.GoodsForm;
import com.example.demo.service.ImageStorageService;

@Service
public class GoodsDaoService {
	
	@Autowired
    private GoodsDao goodsDao;
	
	@Autowired
	private ImageStorageService imageStorageService;
	
    public void createDao(GoodsForm form) { //作成
    	System.out.println("===== create_Dao =====");

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

        goodsDao.insert(goods);
    }
    
    public void updateDao(GoodsForm form) { //更新
    	System.out.println("===== update_Dao =====");
    	
    	Goods goods = goodsDao.selectById(form.getId());
    	if (goods == null) return;
    	
    	goods.setName(form.getName());
        goods.setDescription(form.getDescription());
        goods.setSmallCategoryId(form.getSmallCategoryId());
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

        goodsDao.update(goods);
    }
    
    public void deleteDao(Integer id) { //削除
    	System.out.println("===== delete_Dao =====");
    	
        Goods goods = goodsDao.selectById(id);
        if (goods == null) return;
        goods.setDeleted(true);
        
        goodsDao.update(goods);
    }

    public PageResult<Goods> search(GoodsSearchFormDao form) {

    	int totalCount =
                goodsDao.countByCondition(
                        form.getKeyword(),
                        form.getSmallCategoryId()
                );

        List<Goods> list =
                goodsDao.searchByCondition(
                        form.getKeyword(),
                        form.getSmallCategoryId(),
                        form.getSize(),
                        form.getOffset()
                );

        int totalPages =
                (int) Math.ceil((double) totalCount / form.getSize());

        return new PageResult<>(
                list,
                form.getPage(),
                totalPages,
                totalCount
        );
    }
}