package com.dncode.dnfood.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dncode.dnfood.domain.model.Restaurante;
import com.dncode.dnfood.domain.repository.RestauranteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(long codigo) {
		return manager.find(Restaurante.class, codigo);
	}

	@Transactional
	@Override
	public Restaurante salvar(Restaurante restaurante) {
		
		return manager.merge(restaurante);
	}

	@Transactional
	@Override
	public void remover(long codigo) {
		Restaurante restaurante = buscar(codigo);
		
		manager.remove(restaurante);
	}
}
