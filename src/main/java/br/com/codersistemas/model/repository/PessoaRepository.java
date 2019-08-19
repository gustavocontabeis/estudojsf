package br.com.codersistemas.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.codersistemas.exceptions.RepositoryException;
import br.com.codersistemas.model.entity.Pessoa;
import br.com.codersistemas.model.entity.Usuario;
import br.com.codersistemas.uteis.Uteis;

public class PessoaRepository {

	private EntityManager entityManager;

	private Logger LOG = LoggerFactory.getLogger(PessoaRepository.class.getSimpleName());

	public void salvar(Pessoa pessoa) throws RepositoryException {
		try {
			if (pessoa.getId() != null) {
				atualizar(pessoa);
			} else {
				entityManager = Uteis.jpaEntityManager();
				Usuario usuario = entityManager.find(Usuario.class, pessoa.getUsuario().getId());
				pessoa.setUsuario(usuario);
				entityManager.persist(pessoa);
				entityManager.flush();
			}

		} catch (Exception e) {
			tratarExcessaoRepositorio(e);
		}
	}

	private void tratarExcessaoRepositorio(Exception e) throws RepositoryException {
		if (e instanceof PersistenceException) {
			Throwable cause = e.getCause();
			if (cause instanceof ConstraintViolationException) {
				ConstraintViolationException ce = (ConstraintViolationException) cause;
				try {
					Configuration config = new PropertiesConfiguration("classpath:constraintsMessages.properties");
					String mensagemConstraint = config.getString(ce.getConstraintName());
					throw new RepositoryException(mensagemConstraint);
				} catch (ConfigurationException constrEx) {
					constrEx.printStackTrace();
				}
			}
			throw new RepositoryException(e.getMessage());
		} else {
			throw new RepositoryException(e.getMessage());
		}
	}

	private void atualizar(Pessoa pessoaModel) throws RepositoryException {
		try {
			entityManager = Uteis.jpaEntityManager();
			Pessoa find = entityManager.find(Pessoa.class, pessoaModel.getId());
			find.setDataCadastro(pessoaModel.getDataCadastro());
			find.setEmail(pessoaModel.getEmail());
			find.setEndereco(pessoaModel.getEndereco());
			find.setNome(pessoaModel.getNome());
			find.setCpf(pessoaModel.getCpf());
			find.setOrigemCadastro(pessoaModel.getOrigemCadastro());
			find.setSexo(pessoaModel.getSexo());
			find.setId(pessoaModel.getId());
			find.setUsuario(pessoaModel.getUsuario());
			entityManager.merge(find);
		} catch (Exception e) {
			tratarExcessaoRepositorio(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> getPessoas() {
		entityManager = Uteis.jpaEntityManager();
		Query query = entityManager.createQuery("select obj from br.com.codersistemas.model.entity.Pessoa obj");
		return query.getResultList();
	}

	public void excluir(Long id) throws RepositoryException {
		try {
			entityManager = Uteis.jpaEntityManager();
			Pessoa find = entityManager.find(Pessoa.class, id);
			if (find != null) {
				entityManager.remove(find);
				entityManager.flush();
			}
		} catch (Exception e) {
			tratarExcessaoRepositorio(e);
		}
	}

	public Pessoa buscar(Long id) throws RepositoryException {
		try {
			entityManager = Uteis.jpaEntityManager();
			Pessoa find = entityManager.find(Pessoa.class, id);
			if (find != null) 
				return find;
		} catch (Exception e) {
			tratarExcessaoRepositorio(e);
		}
		return null;
	}
}
