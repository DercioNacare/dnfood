package com.dncode.dnfood.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dncode.dnfood.domain.model.Permissao;
import com.dncode.dnfood.domain.repository.PermissaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository
{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permissao> listar() {
		return manager.createQuery("from Permissao", Permissao.class).getResultList();
	}

	@Override
	public Permissao buscar(long codigo) {
		return manager.find(Permissao.class, codigo);
	}

	@Override
	@Transactional
	public Permissao salvar(Permissao permissao) {
		
		return manager.merge(permissao);
	}

	@Override
	@Transactional
	public void remover(long codigo) {
		Permissao permissao = buscar(codigo);
		
		manager.remove(permissao);
	}
}
