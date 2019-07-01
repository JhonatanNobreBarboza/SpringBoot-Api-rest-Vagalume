/**
 * 
 */
package com.vagalume.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * @author jh_nb
 *
 */
public interface ArtistaRepositorio extends CrudRepository<Artista, Integer> {
	public List<Artista> findAll();
	
	public Artista findByNome(String nome);
	
	
}
