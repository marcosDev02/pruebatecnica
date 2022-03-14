package com.prueba.tecnica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.tecnica.entity.Heroes;

@Repository
public interface HeroesRepository extends JpaRepository<Heroes, Long>{

	public List<Heroes> findAllHeroesById(Heroes id);
	
	public List<Heroes> findAllHeroesByNombreContaining(String param);
}
