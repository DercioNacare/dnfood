package com.dncode.dnfood.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dncode.dnfood.domain.model.Cozinha;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class CadastroCozinha 
{
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public Cozinha adicionar(Cozinha cozinha)
	{
		return manager.merge(cozinha);
	}
	public List<Cozinha> listar()
	{
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
}
