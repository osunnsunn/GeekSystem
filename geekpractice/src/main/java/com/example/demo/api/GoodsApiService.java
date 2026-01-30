package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.GoodsRepository;

@Service
public class GoodsApiService {

	@Autowired
	private GoodsRepository goodsRepository;
	
	public List<GoodsApiData> getGoodsList() {
        return goodsRepository.findAll()
                .stream()
                .map(g -> new GoodsApiData(
                        g.getId(),
                        g.getName(),
                        g.getImage()
                ))
                .toList();
    }

}
