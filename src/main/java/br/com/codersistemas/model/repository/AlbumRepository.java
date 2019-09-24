package br.com.codersistemas.model.repository;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.codersistemas.uteis.Uteis;

public class AlbumRepository<Album, Long> extends BaseRepository {

	private Logger LOG = LoggerFactory.getLogger(AlbumRepository.class.getSimpleName());

	@SuppressWarnings("unchecked")
	public List<Album> listar() {
		entityManager = Uteis.jpaEntityManager();
		Query query = entityManager.createQuery("select obj from br.com.codersistemas.model.entity.Album obj");
		return query.getResultList();
	}

	@Override
	protected Class getClasse() {
		return new br.com.codersistemas.model.entity.Album().getClass();
	}

}
