package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.http.fileupload.FileItemStream.ItemSkippedException;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Models.Item;
import com.example.demo.Models.OrderItem;

@Controller
@RequestMapping("/")
public class OrderBoardController {

	@Autowired
	private OrderRepository orderRepository;

	@PostConstruct
	public void init() {
		OrderItem order1 = new OrderItem("Household");
		OrderItem order2 = new OrderItem("Groceries");
		OrderItem order3 = new OrderItem("Household");
		order1.setItems(Arrays.asList(new Item("Bleach", true), new Item("Mop")));
		order2.setItems(Arrays.asList(new Item("Apples"), new Item("Potatoes"), new Item("Eggs")));

		orderRepository.save(order1);	
		orderRepository.save(order2);
		orderRepository.save(order3);


	}

	// private List<Order> orders = new ArrayList<>();
	// private List<Item> items = new ArrayList<>();

	@GetMapping(value = "/")

	public String main(Model model, Pageable page) {

		Page<OrderItem> orders = orderRepository.findAll(page);

		model.addAttribute("orders", orders);

		model.addAttribute("showNext", !orders.isLast());
		model.addAttribute("showPrev", !orders.isFirst());
		model.addAttribute("numPage", orders.getNumber() + 1);
		model.addAttribute("nextPage", orders.getNumber() + 1);
		model.addAttribute("prevPage", orders.getNumber() - 1);

		return "board";
	}

	@GetMapping("/new_order")
	public String newForm(Model model) {

		return "order_form";

	}

	@PostMapping("/save_order")
	public String saveOrder(Model model, OrderItem order, Item item) {

		order.setItem(item);

		orderRepository.save(order);
		model.addAttribute("order", order);

		return "order_saved";

	}

	@PostMapping("/save_order/{id}")
	public String updateOrder(Model model, @PathVariable long id, @ModelAttribute OrderItem order_mod) {

		OrderItem order = orderRepository.findById(id).orElseThrow(NotFoundException::new);

		model.addAttribute("previous", order.getTitle());
		model.addAttribute("id", id);


		order.setTitle(order_mod.getTitle());

		model.addAttribute("new", order_mod.getTitle());

		orderRepository.save(order);

		return "order_updated";

	}


	@GetMapping("/order/{id}")
	public String getOrder(Model model, @PathVariable long id) {

		OrderItem order = orderRepository.findById(id).orElseThrow(NotFoundException::new);

		model.addAttribute("order", order);
		model.addAttribute("items", order.getItems());
		model.addAttribute("deletable",(order.getItems().size()>1)?true:false);
		return "order_view";

	}

	@DeleteMapping("/order/{id}")
	public String deleteOrder(@PathVariable long id) {
	 	orderRepository.deleteById(id);
		return "board";
  
	}

	@GetMapping("/edit_order/{id}")
	public String editOrder(Model model, @PathVariable long id) {
		OrderItem order = orderRepository.findById(id).orElseThrow(NotFoundException::new);

		model.addAttribute("order", order);

		return "order_form";

	}

	@PutMapping("/order/{id}/item")
	public OrderItem putItem(Model model, @PathVariable long id, @RequestBody Item item) {

		OrderItem order = orderRepository.findById(id).orElseThrow(NotFoundException::new);
		
		order.setItem(item);

		orderRepository.save(order);
		return order;

	}
	@PutMapping("/order/{orderId}/item/{itemId}")
	public OrderItem checkItem(Model model, @PathVariable long orderId, @PathVariable long itemId, @RequestBody Boolean check) {
		System.out.println("asasd");
		OrderItem order = orderRepository.findById(orderId).orElseThrow(NotFoundException::new);
		
		order.updateItem(itemId, check);

		orderRepository.save(order);
		return order;

	}


	@DeleteMapping("/order/{orderId}/item/{itemId}")
	public String deleteItem(Model model, @PathVariable long orderId, @PathVariable Long itemId) {
		OrderItem order = orderRepository.findById(orderId).orElseThrow(NotFoundException::new);
		System.out.println("asdasd");

		order.deleteById(itemId);

		orderRepository.save(order);

		model.addAttribute("order", order);
		model.addAttribute("items", order.getItems()); 
		model.addAttribute("deletable",(order.getItems().size()>1)?true:false);

		return "order_view";
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order Not Found")
	public class NotFoundException extends RuntimeException {
	}
}