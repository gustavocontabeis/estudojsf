package br.com.codersistemas.view.controllers;

import javax.faces.event.ActionEvent;

public interface ICrudController {
	
	String novo();
	void salvar(ActionEvent evt);
	void excluir(ActionEvent evt);
	void cancelar(ActionEvent evt);
	String selecionar(Object evt);
	String pesquisar();
	void clonar(ActionEvent evt);

}
