package br.com.codersistemas.model.repository;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.codersistemas.model.entity.Pessoa;
import br.com.codersistemas.model.entity.Usuario;
import br.com.codersistemas.uteis.Uteis;

public class PessoaRepository extends BaseRepository<Pessoa, Long> {

	@SuppressWarnings("unused")
	private Logger LOG = LoggerFactory.getLogger(PessoaRepository.class.getSimpleName());

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> listar() {
		Query query = getEntityManager().createQuery("select obj from br.com.codersistemas.model.entity.Pessoa obj inner join fetch obj.usuario usu ");
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getClasse() {
		return new br.com.codersistemas.model.entity.Pessoa().getClass();
	}
	
	@Override
	protected Pessoa salvarAntes(Pessoa entity) {
		if(entity.getUsuario() != null && entity.getUsuario().getId() != null) {
			Usuario merge = getEntityManager().merge(entity.getUsuario());
			entity.setUsuario(merge);
		}
		return entity;
	}

}
