package com.dncode.dnfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dncode.dnfood.DnfoodApiApplication;
import com.dncode.dnfood.domain.model.FormaPagamento;
import com.dncode.dnfood.domain.repository.FormaPagamentoRepository;
import com.dncode.dnfood.infrastructure.repository.FormaPagamentoRepositoryImpl;

public class FormaPagamentoMain 
{
	public static void main(String[] args) 
	{
		ApplicationContext context = new SpringApplicationBuilder(DnfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository formas = context.getBean(FormaPagamentoRepositoryImpl.class);
		
		formas.remover(2l);
		
		FormaPagamento forma = new FormaPagamento();
		forma.setDescricao("Fiado");

		formas.salvar(forma);
		for(FormaPagamento f : formas.liscar())
		{
			System.out.println(">>> " +f.getDescricao());
		}
		
		FormaPagamento f = formas.buscar(1l);
		System.out.println(">>> " +f.getDescricao());
	}
}
