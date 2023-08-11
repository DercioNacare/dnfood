package com.dncode.dnfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dncode.dnfood.domain.exception.EntidadeEmUsoException;
import com.dncode.dnfood.domain.exception.EntidadeNaoEncontradaException;
import com.dncode.dnfood.domain.model.Restaurante;
import com.dncode.dnfood.domain.repository.RestauranteRepository;
import com.dncode.dnfood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController 
{
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@GetMapping
	public List<Restaurante> listar()
	{
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Restaurante> buscar(@PathVariable("codigo") Long codigo)
	{
		Optional<Restaurante> restaurante = restauranteRepository.findById(codigo);
		
		if(restaurante.isPresent())
		{
			return ResponseEntity.ok(restaurante.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody Restaurante restaurante)
	{
		try
		{
			restaurante = restauranteService.cadastrar(restaurante);
		
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		}
		catch(EntidadeNaoEncontradaException ex)
		{
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<?> actualizar(@PathVariable("codigo") Long codigo, @RequestBody Restaurante restaurante)
	{
		Optional<Restaurante> restauranteSalvo = restauranteRepository.findById(codigo);
		
		if(restauranteSalvo.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		try
		{
			BeanUtils.copyProperties(restaurante, restauranteSalvo.get(), "codigo");
			return ResponseEntity.ok(restauranteService.cadastrar(restauranteSalvo.get()));
		}
		catch(EntidadeNaoEncontradaException ex)
		{
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> remover(@PathVariable("codigo") Long codigo)
	{
		try
		{
			restauranteService.remover(codigo);
			return ResponseEntity.noContent().build();
		}
		catch(EntidadeNaoEncontradaException ex)
		{
			return ResponseEntity.notFound().build();
		}
		catch(EntidadeEmUsoException ex)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
	
	@PatchMapping("/{codigo}")
	public ResponseEntity<?> actualizarParcial(@PathVariable("codigo") Long codigo, @RequestBody Map<String, Object> campos)
	{
		Optional<Restaurante> restaurante = restauranteRepository.findById(codigo);
		if(restaurante.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		merge(campos, restaurante.get());
		return actualizar(codigo, restaurante.get());
	}
	private void merge(Map<String, Object> campos, Restaurante restauranteDestino)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(campos, Restaurante.class);
		
		
		campos.forEach((propriedade, valor)->{
			Field field = ReflectionUtils.findField(Restaurante.class, propriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
}