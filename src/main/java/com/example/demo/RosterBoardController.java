package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RosterBoardController {

	@Autowired
	private RosterRepository repository;

	//private List<Order> orders = new ArrayList<>();
	//private List<Item> items = new ArrayList<>();

	@PostConstruct
	public void init() {
		for(int i=0; i<100; i++){
			repository.save(new Roster("Pepe", "Anuncio"+i, "XXXX"));
		}		
	}

	@RequestMapping("/")
	public String board(Model model, Pageable page) {
		
		System.out.println(page);

		Page<Roster> rosters = repository.findAll(page);
		
		model.addAttribute("rosters", rosters);
		
		model.addAttribute("showNext", !rosters.isLast());
		model.addAttribute("showPrev", !rosters.isFirst());
		model.addAttribute("numPage", rosters.getNumber());
		model.addAttribute("nextPage", rosters.getNumber()+1);
		model.addAttribute("prevPage", rosters.getNumber()-1);
		
		return "board";
	}
	@RequestMapping("/roster/new")
	public String newRoster(Model model, Roster roster ) {

		repository.save(roster);

		return "roster_saved";

	}

	@RequestMapping("/roster/{id}")
	public String viewOrder(Model model, @PathVariable long id) {

		Roster roster =  repository.findOne(id);

		model.addAttribute("roster", roster);

		return "ver_anuncio";
	}
}