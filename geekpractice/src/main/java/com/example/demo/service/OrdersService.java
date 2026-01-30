package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderDetails;
import com.example.demo.entity.Orders;
import com.example.demo.form.OrderForm;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.OrderDetailsRepository;
import com.example.demo.repository.OrdersRepository;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private GoodsRepository goodsRepository;
    
    @Autowired
    private StoreStocksService storeStocksService;

    @Transactional
    public void createOrder(OrderForm form) {

    	Orders order = new Orders();
        order.setUsersId(form.getUsersId());
        order.setStoresId(form.getStoresId());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        ordersRepository.save(order);

        OrderDetails details = new OrderDetails();
        details.setOrdersId(order.getId());
        details.setGoodsId(form.getGoodsId());
        details.setQuantity(form.getQuantity());
        details.setCreatedAt(LocalDateTime.now());
        details.setUpdatedAt(LocalDateTime.now());
        orderDetailsRepository.save(details);

        storeStocksService.addStock(form.getStoresId(), form.getGoodsId(), form.getQuantity());
    }
    
    @Transactional
    public List<Orders> getOrdersHistoryByStore(Integer storeId) {
        return ordersRepository.findByStoresIdWithDetails(storeId);
    }
  
}