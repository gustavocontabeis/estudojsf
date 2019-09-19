package br.com.codersistemas.model.entity;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import br.com.codersistemas.annotations.AppLogger;

@Named(value="pessoaEvents")
public class PessoaEvents {
	
	//private Logger LOG = LoggerFactory.getLogger(PessoaEvents.class.getSimpleName());
	
	@Inject
	@AppLogger
	private java.util.logging.Logger LOG;
	
	@PrePersist 
	public void antesDePersistir(Pessoa pessoa) {
		//LOG.info("Antes de persistir: "+pessoa.toString());
		pessoa.setDataCadastro(LocalDateTime.now());
	}

	@PreUpdate
	public void antesDeAtualizar(Pessoa pessoa) {
		//LOG.info("Antes de atualizar: "+pessoa.toString());
		//pessoa.setDataCadastro(LocalDateTime.now());
	}

	@PreRemove
	public void antesDeRemover(Pessoa pessoa) {
		//LOG.info("Antes de remover: "+pessoa.toString());
		//pessoa.setDataCadastro(LocalDateTime.now());
	}

}
