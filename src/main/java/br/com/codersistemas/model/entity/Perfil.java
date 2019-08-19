package br.com.codersistemas.model.entity;

public enum Perfil {
	
	ADMIN("Administrador"), MANAGER("Gerente"), USER("Usu�rio");
	
	private String descricao;
	
	Perfil(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
