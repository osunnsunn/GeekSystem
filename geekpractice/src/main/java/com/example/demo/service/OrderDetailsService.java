package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderDetails;
import com.example.demo.repository.OrderDetailsRepository;

@Service
public class OrderDetailsService {

	@Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> getOrdersBetween(LocalDateTime start, LocalDateTime end) {
        return orderDetailsRepository.findByCreatedAtBetween(start, end);
    }
}
