package com.example.demo.Models;

import javax.persistence.*;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean checked;
	private String name;	
	
	
	protected Item() {
		
	}

	public Item (String name) {
		super();
		this.name = name;
		this.checked = false;
	}

	public Item (String name, Boolean checked) {
		super();
		this.name = name;
		this.checked = checked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}