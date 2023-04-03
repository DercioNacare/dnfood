package com.dncode.dnfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dncode.dnfood.DnfoodApiApplication;
import com.dncode.dnfood.domain.model.Cozinha;
import com.dncode.dnfood.infrastructure.repository.CozinhaRepositoryImpl;

public class CozinhaMain 
{
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(DnfoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);
		
		CozinhaRepositoryImpl cozinhas = context.getBean(CozinhaRepositoryImpl.class);
	
		
		Cozinha coz = new Cozinha();
		coz.setNome("Moçambicana");
	
		cozinhas.salvar(coz);
		cozinhas.remover(2l);
		for(Cozinha cozinha: cozinhas.listar())
		{
			System.out.println(cozinha.getNome());
		}
		System.out.println("::: " + cozinhas.buscar(1L).getNome());
	}
}
