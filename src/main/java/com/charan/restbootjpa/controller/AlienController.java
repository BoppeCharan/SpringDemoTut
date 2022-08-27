package com.charan.restbootjpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.charan.restbootjpa.dao.AlienRepo;
import com.charan.restbootjpa.model.*;
@Controller
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	
	@RequestMapping("/")
	public ModelAndView Alien() {
		
		System.out.println("Hi Alien");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home.jsp");
		return mv;
	}
//	

	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		return "home.jsp";
	}
	
	@RequestMapping("getAlien")
	public ModelAndView getAlien(@RequestParam int aid) {
		
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		
		
	
		System.out.println(repo.findByTech("java"));
		System.out.println(repo.findByAidGreaterThan(102));
		
		System.out.println(repo.findByTechSorted("java"));
		Alien alien =  repo.findById(aid).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}
	
	@RequestMapping("/aliens")
	@ResponseBody
	public List<Alien> getAllAliens() {
		
		return repo.findAll();
	}
	
	
	@RequestMapping("/aliens/{aid}")
	@ResponseBody
	public Optional<Alien> getAlienById(@PathVariable("aid") int aid) {
		
		return repo.findById(aid);
	}
	
	
	@PostMapping("/addAlien")
	@ResponseBody
	public Alien addAlienPost(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	
	@DeleteMapping("/alien/{aid}")
	@ResponseBody
	public String deleteAlien(@PathVariable("aid") int aid) {
		
		@SuppressWarnings("deprecation")
		Alien alien = repo.getOne(aid);
		
		repo.delete(alien);
		return "Deleted";
	}
	
	
	@PutMapping("/alien")
	@ResponseBody
	public Alien UpdateAlien(@RequestBody Alien alien) {
		
		repo.save(alien);
		return alien;
	}
	
}



//INSERT INTO ALIEN (aid, aname)
//VALUES (101,'charan');