package com.prueba.tecnica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="heroes")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Heroes {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String nombre;
}
