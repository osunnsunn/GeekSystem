package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StoreStocks;

public interface StoreStocksRepository extends JpaRepository<StoreStocks, Integer> {
    Optional<StoreStocks> findByStoresIdAndGoodsId(Integer storesId, Integer goodsId);
}