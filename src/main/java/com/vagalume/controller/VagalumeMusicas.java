package com.vagalume.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vagalume.models.Artista;
import com.vagalume.models.Musica;
import com.vagalume.models.Pesquisa;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vagalume.controller.API;
import com.vagalume.controller.GetRequestRepository;
import com.vagalume.dao.MusicaRepository;

@Controller
public class VagalumeMusicas {

	@Autowired
	private MusicaRepository musicaRepository;
	
	private Musica musica = new Musica();
	private Artista cantor = new Artista();
	
	@GetMapping("/")
	private String home(Model model) {
		model.addAttribute("artista", new Pesquisa());
		return "artistaform";
	}
	
	@PostMapping("/buscar")
	public String salvar(@ModelAttribute Pesquisa artista, Model model) {
		API api = new API();
	    GetRequestRepository repository = new GetRequestRepository(api);
	    JsonObject result = repository.getAll(artista.getCantor(), artista.getMusica());
	    JsonObject object = (JsonObject) result.get("art");
	    
	    cantor.setName( object.get("name").toString().replaceAll("\"", "") );
	    cantor.setUrl( object.get("url").toString().replaceAll("\"", "") );
	    
	    JsonArray array = (JsonArray) result.get("mus");
	    List <Musica> musicas = new ArrayList<>();
	    
	    for (int i = 0; i < array.size(); i++) {	
	    	JsonObject m = array.get(i).getAsJsonObject();
	    	musica.setName( m.get("name").toString().replaceAll("\"", "") );
	    	musica.setUrl( m.get("url").toString().replaceAll("\"", "") );
	    	
	    	musica.setText( m.get("text").toString().replaceAll("\"", "") );
	    	musica.setText( musica.getText().replaceAll("\\\\n", "<br>") );
	    	musica.setCantor(cantor.getName());
	    	musicas.add(musica);
	    }
	    
	    model.addAttribute("cantor", cantor);
	    model.addAttribute("musica", musica);
		return "artistalista";		
	}
	
	@GetMapping("/salvarMusica")
	public String SalvarMusica(Model model) {
		musicaRepository.save(musica);
		musica = new Musica();
		return "redirect:listarMusicas";		
	}
	
	@GetMapping("/listarMusicas")
	public String ListarMusicas(Model model) {
		List<Musica> musicas = new ArrayList<>();
		musicas = musicaRepository.findAll();
		model.addAttribute("musicas", musicas);
		return "musicalista";		
	}
	
	@GetMapping("/musicasRestApi")
	public String ListarMusicasApi() {
		return "musicasRestApi";		
	}
	
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable ("id") Integer id) {
		Musica mus = musicaRepository.findMusicaById(id);
		musicaRepository.delete(mus);
		return "redirect:/listarMusicas";
	}
}
