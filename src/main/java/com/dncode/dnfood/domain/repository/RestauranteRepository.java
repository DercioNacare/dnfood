package com.dncode.dnfood.domain.repository;

import java.util.List;

import com.dncode.dnfood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> listar();
	Restaurante buscar(long codigo);
	Restaurante salvar(Restaurante restaurante);
	void remover(long codigo);
}
