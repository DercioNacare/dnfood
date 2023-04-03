package com.dncode.dnfood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dncode.dnfood.DnfoodApiApplication;
import com.dncode.dnfood.domain.model.Restaurante;
import com.dncode.dnfood.infrastructure.repository.RestauranteRepositoryImpl;

public class RestauranteMain 
{
	public static void main(String[] args) 
	{
		ApplicationContext context = new SpringApplicationBuilder(DnfoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);
	
		RestauranteRepositoryImpl res = context.getBean(RestauranteRepositoryImpl.class);
	
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Moçambicano");
		restaurante.setTaxaFrete(BigDecimal.valueOf(340));
		res.salvar(restaurante);
		
		res.remover(1l);
		for(Restaurante r : res.listar())
		{
			System.out.println(r.getNome() + " - " + r.getTaxaFrete());
		}
		
		Restaurante r = res.buscar(2l);
		System.out.println(">>> " + r.getNome() + " - " + r.getTaxaFrete());
		
	}
}
