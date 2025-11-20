package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Makers;
import com.example.demo.form.MakersForm;
import com.example.demo.repository.MakersRepository;

@Service
public class MakersService {
	
	@Autowired
	private MakersRepository makersRepository;
	
	public Makers getMakersById(Integer id) {
		return makersRepository.findById(id).orElse(null);
	}
	
	public List<Makers> getAllMakers(){
		return makersRepository.findAll();
	}
	
	public Makers createMakers(MakersForm form) {
		Makers makers = new Makers();
		makers.setName(form.getName());
		makers.setCreatedAt(LocalDateTime.now());
		makers.setUpdatedAt(LocalDateTime.now());
		
		return makersRepository.save(makers);
	}

}
