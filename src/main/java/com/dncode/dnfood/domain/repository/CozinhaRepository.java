package com.dncode.dnfood.domain.repository;

import java.util.List;

import com.dncode.dnfood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> listar();
	Cozinha buscar(long codigo);
	Cozinha salvar(Cozinha cozinha);
	void remover(long codigo);
}
