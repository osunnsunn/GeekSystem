package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LargeCategory;
import com.example.demo.entity.MiddleCategory;
import com.example.demo.entity.SmallCategory;
import com.example.demo.repository.LargeCategoryRepository;
import com.example.demo.repository.MiddleCategoryRepository;
import com.example.demo.repository.SmallCategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private LargeCategoryRepository largeCategoryRepository;
	
	@Autowired
	private MiddleCategoryRepository middleCategoryRepository;
	
	@Autowired
	private SmallCategoryRepository smallCategoryRepository;
	
	public List<LargeCategory> getAllLargeCategory(){
		return largeCategoryRepository.findAll();
	}
	
	public List<MiddleCategory> getAllMiddleCategory(){
		return middleCategoryRepository.findAll();
	}
	
	public List<SmallCategory> getAllSmallCategory(){
		return smallCategoryRepository.findAll();
	}
	
	public LargeCategory getLargeCategoryById(Integer id) {
        return largeCategoryRepository.findById(id).orElse(null);
    }
	
	public MiddleCategory getMiddleCategoryById(Integer id) {
        return middleCategoryRepository.findById(id).orElse(null);
    }
	
	public SmallCategory getSmallCategoryById(Integer id) {
        return smallCategoryRepository.findById(id).orElse(null);
    }
	

}
