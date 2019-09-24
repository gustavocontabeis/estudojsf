package br.com.codersistemas.model.repository;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.codersistemas.uteis.Uteis;

public class PessoaRepository<Pessoa, Long> extends BaseRepository {

	private Logger LOG = LoggerFactory.getLogger(PessoaRepository.class.getSimpleName());

	@SuppressWarnings("unchecked")
	public List<Pessoa> listar() {
		entityManager = Uteis.jpaEntityManager();
		Query query = entityManager.createQuery("select obj from br.com.codersistemas.model.entity.Pessoa obj inner join fetch obj.usuario usu ");
		return query.getResultList();
	}

	@Override
	protected Class getClasse() {
		return new br.com.codersistemas.model.entity.Pessoa().getClass();
	}

}
