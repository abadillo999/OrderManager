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
import com.example.demo.Models.Roster;

@Controller
public class RosterBoardController {

	@Autowired
	private RosterRepository rosterRepository;
	private ItemRepository itemRepository;

	//private List<Order> orders = new ArrayList<>();
	//private List<Item> items = new ArrayList<>();

	@PostConstruct
	public void init() {
	
	}

	@RequestMapping("/")
	public String board(Model model, Pageable page) {
		
		Page<Roster> rosters = rosterRepository.findAll(page);
		
		model.addAttribute("rosters", rosters);
		
		model.addAttribute("showNext", !rosters.isLast());
		model.addAttribute("showPrev", !rosters.isFirst());
		model.addAttribute("numPage", rosters.getNumber()+1);
		model.addAttribute("nextPage", rosters.getNumber()+1);
		model.addAttribute("prevPage", rosters.getNumber()-1);
		
		return "board";
	}
	@RequestMapping("/roster/new")
	public String newRoster(Model model, Roster roster ) {

		rosterRepository.save(roster);

		return "roster_saved";

	}
	@RequestMapping("/roster/new1")
	public String newRosterItem(Model model, Roster roster, Item item ) {
		System.out.println(item.getDescription());
		System.out.println(item.getName());
		//rosterRepository.save(roster);
		//itemRepository.save(item);

		roster.setItem(item);
		rosterRepository.save(roster);
		return "roster_saved";

	}

	@RequestMapping("/roster/{id}")
	public String viewOrder(Model model, @PathVariable long id) {

		Roster roster =  rosterRepository.findOne(id);

		model.addAttribute("roster", roster);
		model.addAttribute("items", roster.getItems());
		
		System.out.println(roster.getItems().size());
		System.out.println(roster.getItems().size());

		return "ver_anuncio";
	}
}