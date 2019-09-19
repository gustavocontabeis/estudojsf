package br.com.codersistemas.model.entity;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import br.com.codersistemas.exceptions.RepositoryException;
import br.com.codersistemas.model.repository.PessoaRepository;
import br.com.codersistemas.model.repository.UsuarioRepository;
import br.com.codersistemas.uteis.Uteis;

@Named(value="pessoaEvents")
public class PessoaEvents {
	
	//private Logger LOG = LoggerFactory.getLogger(PessoaEvents.class.getSimpleName());
	private Logger LOG = Logger.getLogger(PessoaEvents.class.getSimpleName());
	
//	@Inject
//	@AppLogger
//	private java.util.logging.Logger LOG;
	
	//@Inject transient 
	//private UsuarioRepository usuarioRepository;

	//@Inject transient 
	//private PessoaRepository usuarioRepository2;

	@PrePersist 
	public void antesDePersistir(Pessoa pessoa) {
		//LOG.info("Antes de persistir: "+pessoa.toString());
		pessoa.setDataCadastro(LocalDateTime.now());
	}

	@PreUpdate
	public void antesDeAtualizar(Pessoa pessoa) {
		LOG.info("-----------------------------");
		
		//EntityManager entityManager = Uteis.jpaEntityManager();
		//Usuario find = entityManager.find(Usuario.class, pessoa.getUsuario().getId());
		//LOG.info(find.toString());

//		Usuario buscar;
//		try {
//			buscar = usuarioRepository.buscar(pessoa.getUsuario().getId());
//			pessoa.setUsuario(buscar);
//		} catch (RepositoryException e) {
//			e.printStackTrace();
//		}
	}

	@PreRemove
	public void antesDeRemover(Pessoa pessoa) {
		//LOG.info("Antes de remover: "+pessoa.toString());
		//pessoa.setDataCadastro(LocalDateTime.now());
	}

}
