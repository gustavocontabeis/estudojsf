package br.com.codersistemas.view.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codersistemas.model.entity.Letra;
import br.com.codersistemas.model.repository.LetraRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@AllArgsConstructor 
@NoArgsConstructor 
@Named(value="consultarLetraController")
@ViewScoped
public class ConsultarLetraController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject	transient 
	private Letra obj;
 
	@Produces 
	private List<Letra> list;
 
	@Inject transient
	private LetraRepository<Letra, Long> repository;
 
	@PostConstruct
	public void init(){
		this.list = repository.listar();
	}
	
}