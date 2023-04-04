package com.dncode.dnfood.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dncode.dnfood.domain.model.FormaPagamento;
import com.dncode.dnfood.domain.repository.FormaPagamentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component 
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository
{
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FormaPagamento> liscar() 
	{
		return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
	}

	@Override
	public FormaPagamento buscar(long codigo) 
	{
		return manager.find(FormaPagamento.class, codigo);
	}

	@Override
	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) 
	{
		return manager.merge(formaPagamento);
	}

	@Override
	@Transactional
	public void remover(long codigo) {
		FormaPagamento forma = buscar(codigo);
		
		manager.remove(forma);
	}

}
