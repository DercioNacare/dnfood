package com.dncode.dnfood.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dncode.dnfood.domain.model.Estado;
import com.dncode.dnfood.domain.repository.EstadoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class EstadoRepositoryImpl implements EstadoRepository
{
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> listar() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

	@Override
	public Estado buscar(long codigo) {
		return manager.find(Estado.class, codigo);
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	@Transactional
	public void remover(long codigo) {
		Estado estado = buscar(codigo);
		manager.remove(estado);
	}
}
