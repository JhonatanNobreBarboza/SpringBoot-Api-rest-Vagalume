package com.vagalume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vagalume.dao.MusicaRepository;
import com.vagalume.models.Musica;

@RestController
public class VagalumeMusicasRest {
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@RequestMapping(value = "/minhasmusicas", method = RequestMethod.GET)
	public ResponseEntity<List<Musica>> listarMusicas() {
		List<Musica> musicas = musicaRepository.findAll();
		return new ResponseEntity<List<Musica>>( musicas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/minhasmusicas/{id}", method = RequestMethod.GET)
	public ResponseEntity<Musica> listarPorSalario(@PathVariable("id") Integer id) {
		Musica musica = musicaRepository.findMusicaById(id);
		return new ResponseEntity<Musica>( musica, HttpStatus.OK);
	}
}