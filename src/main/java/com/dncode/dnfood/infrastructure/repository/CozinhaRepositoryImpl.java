package com.dncode.dnfood.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dncode.dnfood.domain.model.Cozinha;
import com.dncode.dnfood.domain.repository.CozinhaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository
{
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cozinha> listar() 
	{
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Override
	public Cozinha buscar(long codigo) 
	{
		return manager.find(Cozinha.class, codigo);
	}

	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	@Override
	public void remover(long codigo) {
		Cozinha cozinha = buscar(codigo);
		manager.remove(cozinha);
	}
}
