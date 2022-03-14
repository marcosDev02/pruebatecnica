package com.prueba.tecnica.service;

import java.util.List;

import com.prueba.tecnica.entity.Heroes;

public interface HeroesService {

	public List<Heroes> getAllHeroes();
	
	public Heroes getHeroeById(Long id);
	
	public Heroes updateHeroeByName(Heroes nombre);
	
	public void deleteHeroe(Long id);
	
	public List<Heroes> getHeroeByParam(String param);
}
