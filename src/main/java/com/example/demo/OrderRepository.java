package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.OrderItem;


public interface OrderRepository extends JpaRepository<OrderItem, Long> {

	//public List<Order> findByNombreAndAsunto(String nombre, String asunto);
	
}
