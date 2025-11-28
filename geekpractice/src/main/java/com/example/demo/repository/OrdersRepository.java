package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	@Query("SELECT o FROM Orders o JOIN FETCH o.orderDetails WHERE o.storesId = :storeId")
    List<Orders> findByStoresIdWithDetails(@Param("storeId") Integer storeId);

}