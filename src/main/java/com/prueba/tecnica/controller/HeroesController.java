package com.prueba.tecnica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.annotations.TimeDuration;
import com.prueba.tecnica.entity.Heroes;
import com.prueba.tecnica.exception.ErrorNotFoundException;
import com.prueba.tecnica.service.HeroesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value="/heroes")
public class HeroesController {

	@Autowired
	private HeroesService heroesService;
	
	@Operation(summary = "Obtención de todos los heroes")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Obtencion de datos obtenida correctamente"),
		@ApiResponse(responseCode = "500", description = "Error al intentar acceder al servidor")
		})
	@GetMapping
	@TimeDuration
	public ResponseEntity<List<Heroes>> listHeroes(){		
		List<Heroes> heroes = heroesService.getAllHeroes();
		if(heroes == null) {
			throw new ErrorNotFoundException("No se ha podido obtener la lista de super heroes");
		}
		return ResponseEntity.ok(heroes);
	}
	
	@Operation(summary = "Obtención de un heroe mediante el id")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Obtencion de datos obtenida correctamente"),
		@ApiResponse(responseCode = "404", description = "Error, no se encuentra el dato solicitado"),
		@ApiResponse(responseCode = "500", description = "Error al intentar acceder al servidor")
		})
	@GetMapping(value = "/{id}")
	@TimeDuration
	public ResponseEntity<Heroes> getHeroeById(@PathVariable("id") Long id){
		Heroes heroe = heroesService.getHeroeById(id);
		if(heroe == null) {
			throw new ErrorNotFoundException("El Id del usuario al que desea acceder no existe");
		}
		return ResponseEntity.ok(heroe);		
	}
	
	@Operation(summary = "Modificación de un heroe mediante el id")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Obtencion de datos obtenida correctamente"),
		@ApiResponse(responseCode = "403", description = "Error, permiso denegados"),
		@ApiResponse(responseCode = "404", description = "Error, no se encuentra el dato solicitado"),
		@ApiResponse(responseCode = "500", description = "Error al intentar acceder al servidor")
		})
	@PutMapping(value = "/{id}")
	@Secured("ROLE_ADMIN")
	@TimeDuration
	public ResponseEntity<Heroes> updateHeroeByName(@PathVariable("id") Long id,@RequestBody Heroes nombre){
		nombre.setId(id);
		Heroes heroeUpdate = heroesService.updateHeroeByName(nombre);	
		if(heroeUpdate == null) {
			throw new ErrorNotFoundException("No existe el super heroe que desea modificar");
		}
		return ResponseEntity.ok(heroeUpdate);	
	}
	
	@Operation(summary = "Eliminación de un heroe mediante el id")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Obtencion de datos obtenida correctamente"),
		@ApiResponse(responseCode = "403", description = "Error, permiso denegado"),
		@ApiResponse(responseCode = "500", description = "Error al intentar acceder al servidor")
		})
	@DeleteMapping(value = "/{id}")
	@Secured("ROLE_ADMIN")
	@TimeDuration
	public ResponseEntity<Object> deleteHeroe(@PathVariable("id") Long id) {
		Heroes heroe = heroesService.getHeroeById(id);		
		if(heroe == null) {
			throw new ErrorNotFoundException("El super heroe que desea elimianr no existe");
		}
			heroesService.deleteHeroe(id);	
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@Operation(summary = "Obtención de los heroes que cumplen con los requisitos del parametro")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Obtencion de datos obtenida correctamente"),
		@ApiResponse(responseCode = "404", description = "Error, no se encuentra el dato solicitado"),
		@ApiResponse(responseCode = "500", description = "Error al intentar acceder al servidor")
		})
	@GetMapping(value = "/nombre")
	@TimeDuration
	public ResponseEntity<List<Heroes>> getHeroeByParam(@RequestParam(name = "nameParam", required = true ) String nameParam){
		List<Heroes> heroes = heroesService.getHeroeByParam(nameParam);
		if(heroes == null) {
			throw new ErrorNotFoundException("No existe heroes con el nombre indicado");
		}
		return ResponseEntity.ok(heroes);
	}
	
}
