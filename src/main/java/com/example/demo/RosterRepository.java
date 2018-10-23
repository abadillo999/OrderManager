package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RosterRepository extends JpaRepository<Roster, Long> {

	//public List<Order> findByNombreAndAsunto(String nombre, String asunto);
	
}
