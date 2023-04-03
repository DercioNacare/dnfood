package com.dncode.dnfood.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante 
{
	@Id
	@EqualsAndHashCode.Include
	private Long codigo;
	
	private String nome;

	private BigDecimal taxaFrete;
}
