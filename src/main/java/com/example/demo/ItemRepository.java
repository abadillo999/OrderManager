package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {
	
}