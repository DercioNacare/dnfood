package com.dncode.dnfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dncode.dnfood.domain.exception.EntidadeEmUsoException;
import com.dncode.dnfood.domain.exception.EntidadeNaoEncontradaException;
import com.dncode.dnfood.domain.model.Estado;
import com.dncode.dnfood.domain.repository.EstadoRepository;

@Service
public class EstadoService 
{
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado cadastrar(Estado estado)
	{
		return estadoRepository.save(estado);
	}

	public Estado actualizar(Long codigo, Estado estado) 
	{
		Estado estadoSalvo = estadoRepository.findById(codigo).orElseThrow(()-> new EntidadeNaoEncontradaException(String.format("Estado de código %d não encontrado", codigo)));
		
		BeanUtils.copyProperties(estado, estadoSalvo, "codigo");
		
		return cadastrar(estadoSalvo);
	}
	
	public void remover(Long codigo)
	{
		Estado estado = estadoRepository.findById(codigo).orElseThrow(()-> new EntidadeNaoEncontradaException(String.format("Estado de código %d não encontrado", codigo)));
		try
		{
			estadoRepository.delete(estado);
		}
		catch(DataIntegrityViolationException ex)
		{
			throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removido, pois está em uso", codigo));
		}
	}
}