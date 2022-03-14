package com.prueba.tecnica.Heroes;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.prueba.tecnica.entity.Heroes;
import com.prueba.tecnica.repository.HeroesRepository;
import com.prueba.tecnica.service.HeroesService;
import com.prueba.tecnica.service.impl.HeroesServiceImpl;

@SpringBootTest
public class HeroesServiceMockTest {

	@Mock
	private HeroesRepository repository;
	
	@Mock
	private HeroesService heroesService;
	
	@BeforeEach
	public void mockingData() {
		MockitoAnnotations.openMocks(this);
		heroesService = new HeroesServiceImpl(repository);
		
		Heroes heroe = Heroes.builder()
				.id(1L)
				.nombre("pedrito")
				.build();
				
		Mockito.when(repository.findById(1L))
			.thenReturn(Optional.of(heroe));
	}
	
	@Test
	public void validateIdAndReturnHeroe() {
		Heroes heroe = heroesService.getHeroeById(1L);
		Assertions.assertThat(heroe.getNombre()).isEqualTo("pedrito");
	}
	
	@Test
	public void validateParamAndReturnHeroe() {
		List<Heroes> heroe = heroesService.getHeroeByParam("dri");
		Assertions.assertThat(heroe.size() == 1);	
	}
	
}
