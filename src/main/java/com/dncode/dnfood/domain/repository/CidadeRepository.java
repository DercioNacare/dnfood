package com.dncode.dnfood.domain.repository;

import java.util.List;

import com.dncode.dnfood.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> listar();
	Cidade buscar(long codigo);
	Cidade salvar(Cidade cidade);
	void remover(long codigo);
}
