package com.example.demo.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	String title;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>();

	protected OrderItem() {

	}

	public OrderItem(String title) {
		super();
		this.title = title;
	}

	public OrderItem(String title, Item item) {
		super();
		this.title = title;
		this.items.add(item);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> newList) {
		this.items = newList;
	}

	public void setItem(Item item) {
		this.items.add(item);		
	}

	public void deleteById(Long itemId) {
		this.items.remove(this.getItem(itemId).get());

	}

	private Optional<Item> getItem(Long itemId) {
		return items.stream().filter(item-> item.getId()==itemId).findAny();
		
	}

	public void updateItem(Long itemId, Boolean check) {
		this.getItem(itemId).get().setChecked(check);

	}

}
