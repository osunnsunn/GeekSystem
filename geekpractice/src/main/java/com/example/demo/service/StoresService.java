package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Stores;
import com.example.demo.repository.StoresRepository;

@Service
public class StoresService {
	
	@Autowired
	private StoresRepository storesRepository;
	
	public List<Stores> getAllStores() {
		return storesRepository.findAll();
	}

}
