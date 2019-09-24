package br.com.codersistemas.view.controllers;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;

import br.com.codersistemas.exceptions.AppException;
import br.com.codersistemas.model.entity.IEntity;
import br.com.codersistemas.model.entity.Pessoa;
import br.com.codersistemas.model.repository.BaseRepository;
import br.com.codersistemas.uteis.ReflectionUtils;
import br.com.codersistemas.uteis.Uteis;

public abstract class CrudController<T extends IEntity, I extends Serializable> implements ICrudController {
	
	protected T obj;
	
	protected void mensagem(AppException e) {
		Uteis.mensagemErro(StringUtils.isNotBlank(e.getMensagem())?e.getMensagem():e.getMessage());
	}
	
	protected void mensagemOK(String msg) {
		Uteis.mensagem(msg);
	}
	
	@PostConstruct
	public void init() {
		getLog().info("inicio");
		novo();
	}
	
	@Override
	public String novo() {
		getLog().info("novo");;
		instanciarObjeto();
		return "cadastro.xhtml";
	}
	
	@Override
	public void salvar(ActionEvent evt) {
		getLog().info("salvar");
		try {
			boolean cadastro = obj.getId() == null;
			getRepository().salvar(obj);
			mensagemOK("Registro " + (cadastro?"cadastrado":"alterado") + " com sucesso");
		} catch (AppException e) {
			mensagem(e);
		}
	}
	
	public void clonar(ActionEvent evt) {
		getLog().severe(String.format("clonar %s", obj.toString()));
		obj = clonar(obj);
		mensagemOK("Clonagem concluída com sucesso.");
	}
	
	@Override
	public void excluir(ActionEvent evt) {
		try {
			getLog().info("excluir");
			getRepository().excluir(Pessoa.class, this.obj.getId());
			mensagemOK("Registro excluído com sucesso.");
			novo();
		} catch (AppException e) {
			mensagem(e);
		}
	}

	@Override
	public void cancelar(ActionEvent evt) {
		try {
			getLog().info("cancelar");
			if(this.obj.getId() != null) {
				obj = (T) getRepository().buscar(this.obj.getId());
			} else {
				novo();
			}
		} catch (AppException e) {
			mensagem(e);
		}
	}
	
	@Override
	public String pesquisar() {
		getLog().info("pesquisar");
		return "consulta.xhtml";
	}
	
	@Override
	public String selecionar(Object obj) {
		getLog().log(Level.INFO, String.format("selecionar %s", obj));
		this.obj = (T) obj;
		return "cadastro.xhtml";//?faces-redirect=true
	}

	protected abstract Logger getLog();

	protected abstract void instanciarObjeto();
	
	protected abstract BaseRepository getRepository();
	
	protected <T> T clonar(T t) {
		try {
			return ReflectionUtils.cloneEntity(t);
		} catch (InstantiationException e) {
			Uteis.mensagemErro(e.getMessage());
		} catch (IllegalAccessException e) {
			Uteis.mensagemErro(e.getMessage());
		} catch (NoSuchFieldException e) {
			Uteis.mensagemErro(e.getMessage());
		} catch (SecurityException e) {
			Uteis.mensagemErro(e.getMessage());
		} catch (IllegalArgumentException e) {
			Uteis.mensagemErro(e.getMessage());
		}
		return null;
	}

}
