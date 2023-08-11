package com.dncode.dnfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dncode.dnfood.domain.exception.EntidadeEmUsoException;
import com.dncode.dnfood.domain.exception.EntidadeNaoEncontradaException;
import com.dncode.dnfood.domain.model.Cidade;
import com.dncode.dnfood.domain.model.Estado;
import com.dncode.dnfood.domain.repository.CidadeRepository;
import com.dncode.dnfood.domain.repository.EstadoRepository;

@Service
public class CidadeService 
{
	@Autowired
	private CidadeRepository cidadeRespository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade cadastrar(Cidade cidade)
	{
		Estado estado = estadoRepository.findById(cidade.getEstado().getCodigo()).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Estado de código %d não encontrado", cidade.getEstado().getCodigo())));
		
		cidade.setEstado(estado);
		
		return cidadeRespository.save(cidade);
	}

	public void remover(Long codigo) 
	{
		try
		{
			Cidade cidade = cidadeRespository.findById(codigo).orElseThrow(()-> new EntidadeNaoEncontradaException(String.format("Cidade de código %d não encotrada", codigo)));
			cidadeRespository.delete(cidade);
		}
		catch(DataIntegrityViolationException ex)
		{
			throw new EntidadeEmUsoException(String.format("Não é possivel remover cozinha de código %d, pois está em usi", codigo));
		}
	}
}