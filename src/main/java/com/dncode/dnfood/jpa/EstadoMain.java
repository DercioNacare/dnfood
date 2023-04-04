package com.dncode.dnfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dncode.dnfood.DnfoodApiApplication;
import com.dncode.dnfood.domain.model.Estado;
import com.dncode.dnfood.domain.repository.EstadoRepository;
import com.dncode.dnfood.infrastructure.repository.EstadoRepositoryImpl;

public class EstadoMain {
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(DnfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estados = context.getBean(EstadoRepositoryImpl.class);
		
		Estado est = new Estado();
		est.setNome("Estado D");
		
		estados.salvar(est);
		estados.remover(2l);
		for(Estado estado: estados.listar())
		{
			System.out.println(">>>> " + estado.getNome());
		}
		
		Estado estado = estados.buscar(1l);
		System.out.println(">>>> " +  estado.getNome());
				
	}
}
