package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StoreStocks;
import com.example.demo.repository.StoreStocksRepository;

@Service
public class StoreStocksService {

    @Autowired
    private StoreStocksRepository storeStocksRepository;

    public void addStock(Integer storesId, Integer goodsId, Integer quantity) {
        Optional<StoreStocks> optStock = storeStocksRepository.findByStoresIdAndGoodsId(storesId, goodsId);

        StoreStocks stock;
        if (optStock.isPresent()) {
            stock = optStock.get();
            stock.setQuantity(stock.getQuantity() + quantity);
            stock.setUpdatedAt(LocalDateTime.now());
        } else {
            stock = new StoreStocks();
            stock.setStoresId(storesId);
            stock.setGoodsId(goodsId);
            stock.setQuantity(quantity);
            stock.setCreatedAt(LocalDateTime.now());
            stock.setUpdatedAt(LocalDateTime.now());
        }

        storeStocksRepository.save(stock);
    }
}