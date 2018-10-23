package com.example.demo.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Roster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	String nombre;
	String asunto;
	String comentario;
	
	@OneToMany(mappedBy="roster")
	private List<Item> items = new ArrayList<>();
	
	protected Roster() {

	}

	public Roster(String nombre, String asunto, String comentario) {
		super();
		this.nombre = nombre;
		this.asunto = asunto;
		this.comentario = comentario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Anuncio [nombre=" + nombre + ", asunto=" + asunto + ", comentario=" + comentario + "]";
	}

}
