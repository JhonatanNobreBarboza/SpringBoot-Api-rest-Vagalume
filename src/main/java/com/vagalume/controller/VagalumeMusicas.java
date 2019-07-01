package com.vagalume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vagalume.models.Artista;
import com.vagalume.models.ArtistaRepositorio;





@Controller
public class VagalumeMusicas {

	@Autowired
	private ArtistaRepositorio artistaRepo;
	
	@GetMapping("/form")
	private String home(Model model) {
		model.addAttribute("artista", new Artista());
		
		return "artistaform";
	}
	
	@PostMapping("/create")
	public String salvar(@ModelAttribute Artista artista) {
		artistaRepo.save(artista);
		
		return "artistamsg";		
	}
	
	@GetMapping("/listarTodos")
	public String listarTodos(Model model) {
		List<Artista> artistas = artistaRepo.findAll();
		
		model.addAttribute("lista", artistas);
		
		return "artistalista";		
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable ("id") Integer id) {
		Artista artista = artistaRepo.findById(id).orElse(null);
		
		if(artista != null) {
			artistaRepo.delete(artista);
		}		
		
		return "redirect:/listarTodos";
	}
	
	@GetMapping("/update/{id}")
	public String alterar(@PathVariable("id") Integer id, Model model) {
		Artista artista = artistaRepo.findById(id).orElse(null);
		
		if(artista != null) {
			model.addAttribute("artista", artista);
		
			return "artistaform";
		}else {
			return "redirect:/listarTodos";
		}
	}
}
