package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Goods;
import com.example.demo.entity.StoreStocks;
import com.example.demo.repository.GoodsRepository;

@Service
public class StoreStockService {

    @Autowired
    private GoodsRepository goodsRepository;

    public List<GoodsStock> getGoodsStockByStore(Integer storeId) {
        List<Goods> goodsList = goodsRepository.findAll();

        return goodsList.stream().map(goods -> {
            int quantity = goods.getStoreStocks().stream()
                    .filter(stock -> stock.getStoresId().equals(storeId))
                    .mapToInt(StoreStocks::getQuantity)
                    .findFirst()
                    .orElse(0);

            return new GoodsStock(goods.getName(), quantity);
        }).toList();
    }

    public static class GoodsStock {
        private String name;
        private int quantity;

        public GoodsStock(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() { return name; }
        public int getQuantity() { return quantity; }
    }
}