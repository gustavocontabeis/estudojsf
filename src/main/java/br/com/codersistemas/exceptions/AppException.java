package br.com.codersistemas.exceptions;

public class AppException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String mensagem;
	
	public AppException(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
