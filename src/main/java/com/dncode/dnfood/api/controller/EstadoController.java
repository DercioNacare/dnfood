package com.dncode.dnfood.api.controller;

import java.util.List;
import java.util.Optional;

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
import com.dncode.dnfood.domain.model.Estado;
import com.dncode.dnfood.domain.repository.EstadoRepository;
import com.dncode.dnfood.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController
{
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public List<Estado> listar()
	{
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Estado> buscar(@PathVariable("codigo") Long codigo)
	{
		Optional<Estado> estado = estadoRepository.findById(codigo);
		
		if(estado.isPresent())
		{
			return ResponseEntity.ok(estado.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Estado> cadastrar(@RequestBody Estado estado)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.cadastrar(estado));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<?> actualizar(@PathVariable("codigo") Long codigo,@RequestBody Estado estado)
	{
		try
		{
			estado = estadoService.actualizar(codigo, estado);
			
			return ResponseEntity.ok(estado);
		}
		catch(EntidadeNaoEncontradaException ex)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> remover(@PathVariable("codigo") Long codigo)
	{
		try
		{
			estadoService.remover(codigo);
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