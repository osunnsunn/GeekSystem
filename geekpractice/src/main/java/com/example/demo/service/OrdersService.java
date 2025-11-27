package com.example.demo.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderDetails;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Users;
import com.example.demo.form.OrderForm;
import com.example.demo.repository.OrderDetailsRepository;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	private UsersRepository usersRepository;

	@Transactional
	public Orders createOrder(OrderForm form, Users user) {

		Orders order = new Orders();
		order.setUsersId(user.getId());
		order.setStoresId(user.getStoresId());
		Orders savedOrder = ordersRepository.save(order);

		OrderDetails details = new OrderDetails();
		details.setOrdersId(savedOrder.getId());
		details.setGoodsId(form.getGoodsId());
//		details.setQuantity(form.getQuantity());
		orderDetailsRepository.save(details);

		return savedOrder;
	}
	
	public List<Users> getAllUsers(){
		return usersRepository.findAll();
	}

}

//11/27途中