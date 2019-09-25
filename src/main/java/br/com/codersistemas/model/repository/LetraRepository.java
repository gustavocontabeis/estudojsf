package br.com.codersistemas.model.repository;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.codersistemas.uteis.Uteis;

public class LetraRepository<Letra, Long> extends BaseRepository {

	private Logger LOG = LoggerFactory.getLogger(LetraRepository.class.getSimpleName());

	@SuppressWarnings("unchecked")
	public List<Letra> listar() {
		entityManager = Uteis.jpaEntityManager();
		Query query = entityManager.createQuery("select obj from br.com.codersistemas.model.entity.Letra obj inner join fetch obj.album alb");
		return query.getResultList();
	}

	@Override
	protected Class getClasse() {
		return new br.com.codersistemas.model.entity.Album().getClass();
	}

}
