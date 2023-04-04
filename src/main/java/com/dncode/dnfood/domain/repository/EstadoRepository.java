package com.dncode.dnfood.domain.repository;

import java.util.List;

import com.dncode.dnfood.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> listar();
	Estado buscar(long codigo);
	Estado salvar(Estado estado);
	void remover(long codigo);
}
