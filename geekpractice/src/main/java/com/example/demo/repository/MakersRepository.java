package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Makers;

public interface MakersRepository extends JpaRepository<Makers, Integer> {
	Optional<Makers> findById(Integer id);
}
