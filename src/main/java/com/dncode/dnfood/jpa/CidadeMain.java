package com.dncode.dnfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dncode.dnfood.DnfoodApiApplication;
import com.dncode.dnfood.domain.model.Cidade;
import com.dncode.dnfood.domain.repository.CidadeRepository;
import com.dncode.dnfood.domain.repository.EstadoRepository;
import com.dncode.dnfood.infrastructure.repository.CidadeRepositoryImpl;
import com.dncode.dnfood.infrastructure.repository.EstadoRepositoryImpl;

public class CidadeMain {
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(DnfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidades = context.getBean(CidadeRepositoryImpl.class);
		EstadoRepository estados = context.getBean(EstadoRepositoryImpl.class);
		
		
		Cidade cid = new Cidade();
		cid.setNome("Cidade C");
		cid.setEstado(estados.buscar(1l));
		
		
		cidades.salvar(cid);
		
		cidades.remover(2l);
		
		for(Cidade cidade: cidades.listar())
		{
			System.out.printf("%s - %s", cidade.getNome(), cidade.getEstado().getNome());
		}
		
		Cidade cidade = cidades.buscar(1l);
		System.out.printf("%s - %s", cidade.getNome(), cidade.getEstado().getNome());
		
	}
}
