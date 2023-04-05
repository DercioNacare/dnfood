package com.dncode.dnfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dncode.dnfood.domain.model.Cozinha;
import com.dncode.dnfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController 
{
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Cozinha> listar()
	{
		return cozinhaRepository.listar();
	}
	
	@GetMapping("/{codigoCozinha}")
	public Cozinha buscar(@PathVariable("codigoCozinha") long codigoCozinha)
	{
		return cozinhaRepository.buscar(codigoCozinha);
	}
}