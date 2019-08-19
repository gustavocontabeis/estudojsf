package br.com.codersistemas.view.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.codersistemas.model.entity.Usuario;
import br.com.codersistemas.model.repository.UsuarioRepository;
import br.com.codersistemas.uteis.Uteis;

@SessionScoped
@Named(value = "usuarioController")
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private Usuario obj;

	public Usuario getObj() {
		return obj;
	}

	public void setObj(Usuario obj) {
		this.obj = obj;
	}

	public Usuario getUsuarioSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (Usuario) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}

	public String efetuarLogin() {
		
		obj.setLogin("admin");
		obj.setSenha("123");
		
		if (StringUtils.isEmpty(obj.getLogin()) || StringUtils.isBlank(obj.getLogin())) {
			Uteis.mensagem("Favor informar o login!");
			return null;
		} else if (StringUtils.isEmpty(obj.getSenha()) || StringUtils.isBlank(obj.getSenha())) {
			Uteis.mensagem("Favor informar a senha!");
			return null;
		} else {
			obj = usuarioRepository.validaUsuario(obj);
			if (obj != null) {
				obj.setSenha(null);
				obj.setCodigo(obj.getId());
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", obj);
				return "sistema/home?faces-redirect=true";
			} else {
				Uteis.mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}

	}

}
