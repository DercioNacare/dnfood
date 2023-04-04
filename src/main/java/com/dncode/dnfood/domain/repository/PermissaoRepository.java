package com.dncode.dnfood.domain.repository;

import java.util.List;

import com.dncode.dnfood.domain.model.Permissao;

public interface PermissaoRepository 
{
	List<Permissao> listar();
	Permissao buscar(long codigo);
	Permissao salvar(Permissao permissao);
	void remover(long codigo);
}
