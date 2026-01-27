package com.example.demo.doma2;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.entity.Goods;
import com.example.demo.form.GoodsSearchForm;

@Dao
@ConfigAutowireable
public interface GoodsDao {
	
	@Select
    List<Goods> searchByCondition(
            String keyword,
            Integer smallCategoryId,
            int limit,
            int offset
    );

    @Select
    int countByCondition(
            String keyword,
            Integer smallCategoryId
    );
	
	@Select
	Goods selectById(Integer id);
	
	@Select
    int count(GoodsSearchForm form);
	
	@Insert
	int insert(Goods goods);
	
	@Update
    int update(Goods goods);

    @Delete
    int delete(Goods goods);
    
}