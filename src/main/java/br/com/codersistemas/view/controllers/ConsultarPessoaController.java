package br.com.codersistemas.view.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codersistemas.model.entity.Pessoa;
import br.com.codersistemas.model.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@AllArgsConstructor 
@NoArgsConstructor 
@Named(value="consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject	transient 
	private Pessoa obj;
 
	@Produces 
	private List<Pessoa> list;
 
	@Inject transient
	private PessoaRepository pessoaRepository;
 
	@PostConstruct
	public void init(){
		this.list = pessoaRepository.listar();
	}
	
}