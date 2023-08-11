package com.dncode.dnfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dncode.dnfood.domain.exception.EntidadeEmUsoException;
import com.dncode.dnfood.domain.exception.EntidadeNaoEncontradaException;
import com.dncode.dnfood.domain.model.Cidade;
import com.dncode.dnfood.domain.repository.CidadeRepository;
import com.dncode.dnfood.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController 
{
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public List<Cidade> listar()
	{
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@PathVariable("codigo") Long codigo)
	{
		Optional<Cidade> cidade = cidadeRepository.findById(codigo);
		
		if(cidade.isPresent())
		{
			return ResponseEntity.ok(cidade);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody Cidade cidade)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.cadastrar(cidade));
		}
		catch(EntidadeNaoEncontradaException ex)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<?> actualizar(@PathVariable("codigo") Long codigo, @RequestBody Cidade cidade)
	{
		Optional<Cidade> cidadeSalva = cidadeRepository.findById(codigo);
		
		if(cidadeSalva.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		try
		{
			BeanUtils.copyProperties(cidade, cidadeSalva.get(), "codigo");
			
			return ResponseEntity.ok(cidadeService.cadastrar(cidadeSalva.get()));
		}
		catch(EntidadeNaoEncontradaException ex)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> remover(@PathVariable("codigo") Long codigo)
	{
		try
		{	cidadeService.remover(codigo);
			return ResponseEntity.noContent().build();
		}
		catch(EntidadeNaoEncontradaException ex)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch(EntidadeEmUsoException ex)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
}