package com.dncode.dnfood.domain.repository;

import java.util.List;

import com.dncode.dnfood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {
	
	List<FormaPagamento> liscar();
	FormaPagamento buscar(long codigo);
	FormaPagamento salvar(FormaPagamento formaPagamento);
	void remover(long codigo);
}
