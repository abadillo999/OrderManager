package com.example.demo.Models;

import javax.persistence.*;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private boolean checked;
	private String name;
	private String description;
	
	

	
	protected Item() {
		
	}

	public Item (String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.checked = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}