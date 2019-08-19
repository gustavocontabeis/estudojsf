package br.com.codersistemas.view.controller;

import javax.faces.event.ActionEvent;

public interface ICrudController {
	
	void novo(ActionEvent evt);
	void salvar(ActionEvent evt);
	void excluir(ActionEvent evt);
	void cancelar(ActionEvent evt);
	String selecionar(Object evt);
	String pesquisar();
	void clonar(ActionEvent evt);

}
