package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	// private List<Order> orders = new ArrayList<>();
	// private List<Item> items = new ArrayList<>();



	@GetMapping(value="/")

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



	@PostMapping("/new_order")
	public String newOrderItem(Model model, OrderItem order, Item item) {

		order.setItem(item);

		orderRepository.save(order);
		model.addAttribute("order", order);


		return "order_saved";

	}

	@RequestMapping("/order/{id}")
	public String viewOrder(Model model, @PathVariable long id) {
	

	OrderItem order = orderRepository.findById(id).orElseThrow(NotFoundException::new);
	

	model.addAttribute("order", order);
	model.addAttribute("items", order.getItems());
	

	return"order_view";

	}

	@RequestMapping("/order/{id}/item")
	public String postItems(Model model, @PathVariable long id , @RequestBody Map<String, String> payload) {

		OrderItem order =  orderRepository.findById(id).orElseThrow(NotFoundException::new);

		model.addAttribute("order", order);
		model.addAttribute("items", order.getItems());
		

		return "order_view";
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order Not Found")
		public class NotFoundException extends RuntimeException{
		}
}