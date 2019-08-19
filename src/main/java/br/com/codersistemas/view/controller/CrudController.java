package br.com.codersistemas.view.controller;

import org.apache.commons.lang3.StringUtils;

import br.com.codersistemas.exceptions.AppException;
import br.com.codersistemas.uteis.ReflectionUtils;
import br.com.codersistemas.uteis.Uteis;

public abstract class CrudController implements ICrudController {
	
	protected void mensagem(AppException e) {
		Uteis.mensagemErro(StringUtils.isNotBlank(e.getMensagem())?e.getMensagem():e.getMessage());
	}
	
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
