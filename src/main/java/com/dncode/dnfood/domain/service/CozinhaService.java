package com.dncode.dnfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dncode.dnfood.domain.exception.EntidadeEmUsoException;
import com.dncode.dnfood.domain.exception.EntidadeNaoEncontradaException;
import com.dncode.dnfood.domain.model.Cozinha;
import com.dncode.dnfood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService 
{
	@Autowired
	private CozinhaRepository cozinhaRepository;
		
	public Cozinha cadastrar(Cozinha cozinha)
	{
		return cozinhaRepository.save(cozinha);
	}
	
	public void remover(Long codigo)
	{
		try
		{
			Cozinha cozinha = cozinhaRepository.findById(codigo).orElseThrow(()-> new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com o código %d ", codigo)));
			cozinhaRepository.delete(cozinha);
		}
		catch(DataIntegrityViolationException ex)
		{
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso", codigo));
		}
	}
}
