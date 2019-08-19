package br.com.codersistemas.view.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codersistemas.model.entity.Pessoa;
import br.com.codersistemas.model.repository.PessoaRepository;
 

 
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
		this.list = pessoaRepository.getPessoas();
	}
	
	public List<Pessoa> getList() {
		return list;
	}
	public void setList(List<Pessoa> pessoas) {
		this.list = pessoas;
	}		
	public Pessoa getObj() {
		return obj;
	}
	public void setObj(Pessoa Pessoa) {
		this.obj = Pessoa;
	}
 
}