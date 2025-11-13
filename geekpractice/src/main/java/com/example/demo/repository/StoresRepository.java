package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Stores;

public interface StoresRepository extends JpaRepository<Stores, Integer> {
	Optional<Stores> findById(Integer id);
}
