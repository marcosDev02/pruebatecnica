package com.prueba.tecnica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.entity.Heroes;
import com.prueba.tecnica.repository.HeroesRepository;
import com.prueba.tecnica.service.HeroesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HeroesServiceImpl implements HeroesService{

	@Autowired
	private final HeroesRepository repository;
	
	@Override
	public List<Heroes> getAllHeroes() {
		return repository.findAll();
	}

	@Override
	public Heroes getHeroeById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Heroes updateHeroeByName(Heroes nombre) {
		Heroes heroe = getHeroeById(nombre.getId());
		heroe.setNombre(nombre.getNombre());
		return repository.save(heroe);
	}

	@Override
	public void deleteHeroe(Long id) {	
		repository.deleteById(id);
	}

	@Override
	public List<Heroes> getHeroeByParam(String param) {
		return repository.findAllHeroesByNombreContaining(param);
		
	}

}
