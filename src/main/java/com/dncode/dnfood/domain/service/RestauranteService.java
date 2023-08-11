package com.dncode.dnfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dncode.dnfood.domain.exception.EntidadeEmUsoException;
import com.dncode.dnfood.domain.exception.EntidadeNaoEncontradaException;
import com.dncode.dnfood.domain.model.Cozinha;
import com.dncode.dnfood.domain.model.Restaurante;
import com.dncode.dnfood.domain.repository.CozinhaRepository;
import com.dncode.dnfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService 
{
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante cadastrar(Restaurante restaurante)
	{
		Long codigoCozinha = restaurante.getCozinha().getCodigo();
		
		Cozinha cozinha = cozinhaRepository.findById(codigoCozinha)
				.orElseThrow(()-> new EntidadeNaoEncontradaException(
						String.format("Não existe cadastro de cozinha com o código %d",codigoCozinha)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}

	public void remover(Long codigo) 
	{
		try
		{
			Restaurante restaurante = restauranteRepository.findById(codigo).orElseThrow(()->new EntidadeNaoEncontradaException(String.format("Restaurante de código %d não encontrado", codigo)));
			restauranteRepository.delete(restaurante);
		}
		catch(DataIntegrityViolationException ex)
		{
			throw new EntidadeEmUsoException(String.format("Não é possivel remover o restaurante de código %d, pois está em uso", codigo));
		}
	}
}