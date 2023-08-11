package com.dncode.dnfood.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String nome;

	private BigDecimal taxaFrete;

	@ManyToOne
	@JoinColumn(name = "codigo_cozinha")
	private Cozinha cozinha;

	@Override
	public String toString() {
		return "Restaurante [codigo=" + codigo + ", nome=" + nome + ", taxaFrete=" + taxaFrete + ", cozinha=" + cozinha
				+ "]";
	}
	
	
}