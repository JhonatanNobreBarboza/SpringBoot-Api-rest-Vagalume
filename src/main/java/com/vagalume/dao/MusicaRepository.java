package com.vagalume.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vagalume.models.Musica;


public interface MusicaRepository extends JpaRepository<Musica, Integer>{

	public Musica findMusicaById(Integer id);
}
