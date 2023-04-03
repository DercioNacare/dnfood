package com.dncode.dnfood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tabela_cozinha")
public class Cozinha 
{
	@Id
	@EqualsAndHashCode.Include
	private long codigo;

	@Column(name = "nom_coz")
	private String nome;
}