package br.com.codersistemas.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
 
@Entity(name="usuario")
//@NamedQuery(name = "UsuarioEntity.findUser", query= "SELECT u FROM UsuarioEntity u WHERE u.login = :usuario AND u.senha = :senha")
public class Usuario implements IEntity {
 
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(generator="usuario_seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="usuario_seq", initialValue=1, sequenceName="usuario_sequence")
	@Column(name="id_usuario")
	private Long id;
 
	@Column(name="data_exclusao", nullable=true)
	private LocalDateTime exclusao;
	
	@Column(name="login",  length=100)
	private String login;
 
	@Column(length=100)
	private String senha;
 
	public Long getId() {
		return id;
	}
	public void setCodigo(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public LocalDateTime getExclusao() {
		return exclusao;
	}
	public void setExclusao(LocalDateTime exclusao) {
		this.exclusao = exclusao;
	}
 
}
