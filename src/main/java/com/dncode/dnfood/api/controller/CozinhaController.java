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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dncode.dnfood.domain.exception.EntidadeEmUsoException;
import com.dncode.dnfood.domain.exception.EntidadeNaoEncontradaException;
import com.dncode.dnfood.domain.model.Cozinha;
import com.dncode.dnfood.domain.repository.CozinhaRepository;
import com.dncode.dnfood.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController 
{
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public List<Cozinha> listarC()
	{
		return cozinhaRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("codigo") Long codigo)
	{
		Optional<Cozinha> cozinha = cozinhaRepository.findById(codigo);
		
		if(cozinha.isPresent())
		{
			return ResponseEntity.ok(cozinha.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Cozinha salvar(@RequestBody Cozinha cozinha)
	{
		return cozinhaService.cadastrar(cozinha);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Cozinha> actualizar(@PathVariable("codigo") Long codigo, @RequestBody Cozinha cozinha)
	{
		Optional<Cozinha> cozinhaSalva = cozinhaRepository.findById(codigo);
		if(cozinhaSalva.isPresent())
		{
			BeanUtils.copyProperties(cozinha, cozinhaSalva.get(), "codigo");
		
			return ResponseEntity.ok(cozinhaService.cadastrar(cozinhaSalva.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> remover(@PathVariable("codigo") Long codigo)
	{
		try
		{
			cozinhaService.remover(codigo);
			return ResponseEntity.noContent().build();			
		}
		catch(EntidadeEmUsoException ex)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch(EntidadeNaoEncontradaException ex)
		{
			return ResponseEntity.notFound().build();
		}
	}
}