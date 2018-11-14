package com.example.demo;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.Item;
import com.example.demo.Models.OrderItem;

@Controller
public class OrderBoardController {

	@Autowired
	private OrderRepository orderRepository;
	private ItemRepository itemRepository;

	//private List<Order> orders = new ArrayList<>();
	//private List<Item> items = new ArrayList<>();

	@PostConstruct
	public void init() {
	
	}

	@RequestMapping("/")
	public String board(Model model, Pageable page) {
		
		Page<OrderItem> orders = orderRepository.findAll(page);
		
		model.addAttribute("orders", orders);
		
		model.addAttribute("showNext", !orders.isLast());
		model.addAttribute("showPrev", !orders.isFirst());
		model.addAttribute("numPage", orders.getNumber()+1);
		model.addAttribute("nextPage", orders.getNumber()+1);
		model.addAttribute("prevPage", orders.getNumber()-1);
		
		return "board";
	}
	@RequestMapping("/order/new")
	public String newOrder(Model model, OrderItem order ) {

		orderRepository.save(order);

		return "order_saved";

	}
	@RequestMapping("/order/new1")
	public String newOrderItem(Model model, OrderItem order, Item item ) {
		System.out.println(item.getDescription());
		System.out.println(item.getName());
		//rosterRepository.save(roster);
		//itemRepository.save(item);

		order.setItem(item);
		orderRepository.save(order);
		return "order_saved";

	}

	@RequestMapping("/order/{id}")
	public String viewOrder(Model model, @PathVariable long id) {

		OrderItem order =  orderRepository.findOne(id);

		model.addAttribute("order", order);
		model.addAttribute("items", order.getItems());
		
		System.out.println(order.getItems().size());
		System.out.println(order.getItems().size());

		return "ver_anuncio";
	}
}