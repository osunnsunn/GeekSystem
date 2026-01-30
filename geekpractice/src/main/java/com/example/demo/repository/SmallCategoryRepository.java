package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SmallCategory;

public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Integer>{
	List<SmallCategory> findByMiddleCategoryId(Integer middleCategoryId);

}
