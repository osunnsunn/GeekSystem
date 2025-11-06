package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Stores;

public interface StoresRepository extends JpaRepository<Stores, Integer> {

}
