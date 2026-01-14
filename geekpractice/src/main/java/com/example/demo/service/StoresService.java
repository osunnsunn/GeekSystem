package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

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

    // ★ 編集画面用：Entity → Form 変換
    public StoresForm getStoresFormById(Integer id) {
        Stores stores = getStoresById(id);
        if (stores == null) {
            return null;
        }

        StoresForm form = new StoresForm();
        form.setName(stores.getName());
        form.setAddress(stores.getAddress());

        return form;
    }
	public Stores createStores(StoresForm form) {
	    Stores stores = new Stores();
	    stores.setName(form.getName());
	    stores.setAddress(form.getAddress());
	    stores.setCreatedAt(LocalDateTime.now());
	    stores.setUpdatedAt(LocalDateTime.now());
	    
	    return storesRepository.save(stores);
	}
	
	public Stores updateStores(Integer id, StoresForm form) {
		Stores stores = storesRepository.findById(id).orElse(null);
	    if (stores == null) return null;
		
	    stores.setName(form.getName());
	    stores.setAddress(form.getAddress());
	    stores.setUpdatedAt(LocalDateTime.now());
	    
	    return storesRepository.save(stores);
	}
}
