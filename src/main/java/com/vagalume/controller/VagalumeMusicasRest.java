package com.vagalume.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vagalume.models.Artista;
import com.vagalume.models.ArtistaRepositorio;



@RestController
public class VagalumeMusicasRest {
	
	ArtistaRepositorio artistaRepo;
	
	@GetMapping("/artistas")
	public List<Artista> getArtista() {
		RestTemplate ar = new RestTemplate();
		
		
		ResponseEntity<List<Artista>> response = ar.exchange("https://api.vagalume.com.br/search.php?art=u2&mus=all&apikey={1c50d8d71555b0e9bde23ff9172dae97}", HttpMethod.GET,
				null, 
				new ParameterizedTypeReference<List<Artista>>() {
				});

		List<Artista> artistas = response.getBody();
		
			
			
		return artistas;
	}

	
	@PostMapping("/artistas")
	public Artista newArtista(@RequestBody Artista artista) {
		return artistaRepo.save(artista);
	}

	@GetMapping("/artistas/{id}")
	public Artista getArtista(@PathVariable Integer id) {
		return artistaRepo.findById(id).orElse(null);
	}

	@PutMapping("/artistas/{id}")
	public Artista editArtista(@RequestBody Artista artista, @PathVariable Integer id) {
		Artista artistaEdit = artistaRepo.findById(id).orElse(null);

		if (artistaEdit == null)
			return null;
		else {
			if(artista.getName() != null)
			artistaEdit.setName(artista.getName());
			
			return artistaRepo.save(artistaEdit);
		}
	}
	
	@DeleteMapping("/artistas/{id}")
	public boolean deleteArtistas(@PathVariable Integer id) {
		
		Artista artista = artistaRepo.findById(id).orElse(null);
		
		if(artista == null)
			return false;
		else {
			artistaRepo.deleteById(id);
		
			return artistaRepo.findById(id).orElse(null) == null ? true : false;
		}
	}

}
