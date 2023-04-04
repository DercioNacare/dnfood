package com.dncode.dnfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dncode.dnfood.DnfoodApiApplication;
import com.dncode.dnfood.domain.model.Permissao;
import com.dncode.dnfood.domain.repository.PermissaoRepository;
import com.dncode.dnfood.infrastructure.repository.PermissaoRepositoryImpl;

public class PermissaoMain {
	public static void main(String[] args) {
		ApplicationContext contex = new SpringApplicationBuilder(DnfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissoes = contex.getBean(PermissaoRepositoryImpl.class);
		
		Permissao per = new Permissao();
		per.setDescricao("Descricao C");
		per.setNome("Permissao C");
		
		permissoes.salvar(per);
		permissoes.remover(2l);
		for(Permissao permissao : permissoes.listar())
		{
			System.out.println(">>> " + permissao.getNome());
		}
		Permissao p = permissoes.buscar(1l);
		System.out.println("::: " +  p.getNome());
	}
}
