package com.dncode.dnfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate5.SpringSessionContext;

import com.dncode.dnfood.DnfoodApiApplication;
import com.dncode.dnfood.domain.model.Cozinha;

public class CozinhaMain 
{
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(DnfoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);
		
		CadastroCozinha cozinhas = context.getBean(CadastroCozinha.class);
	
		
		Cozinha coz = new Cozinha();
		coz.setNome("Brasileira");
	
		cozinhas.adicionar(coz);
		
		for(Cozinha cozinha: cozinhas.listar())
		{
			System.out.println(cozinha.getNome());
		}
	}
}
