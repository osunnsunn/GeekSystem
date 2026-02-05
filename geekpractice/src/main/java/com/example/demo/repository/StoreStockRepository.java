package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StoreStocks;

public interface StoreStockRepository extends JpaRepository<StoreStocks, Integer> {

    List<StoreStocks> findByGoodsId(Integer goodsId);

    List<StoreStocks> findByGoodsIdAndStoresId(Integer goodsId, Integer storeId);
}