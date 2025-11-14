package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Stores;
import com.example.demo.form.StoresForm;
import com.example.demo.repository.StoresRepository;

@Service
public class StoresService {
	
	@Autowired
	private StoresRepository storesRepository;
	
	public List<Stores> getAllStores() {
		return storesRepository.findAll();
	}
	
	public Stores getStoresById(Integer id) {
		return storesRepository.findById(id).orElse(null);
	}
	
	public Stores createStores(StoresForm form) {
	    Stores stores = new Stores();
	    stores.setName(form.getName());
	    stores.setAddress(form.getAddress());
	    stores.setCreatedAt(LocalDateTime.now());
	    stores.setUpdatedAt(LocalDateTime.now());
	    
	    return storesRepository.save(stores);
	}
	
	public Stores updateStores(Integer id, Stores updatedStores) {
		Optional<Stores> opt = storesRepository.findById(id);
		if (opt.isEmpty()) return null;
		
		Stores stores = opt.get();
	    stores.setName(updatedStores.getName());
	    stores.setAddress(updatedStores.getAddress());
	    
	    return storesRepository.save(stores);
	}
}
