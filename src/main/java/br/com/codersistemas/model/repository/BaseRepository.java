package br.com.codersistemas.model.repository;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.hibernate.exception.ConstraintViolationException;

import br.com.codersistemas.exceptions.RepositoryException;
import br.com.codersistemas.model.entity.IEntity;
import br.com.codersistemas.uteis.Uteis;

public abstract class BaseRepository<T extends IEntity, I extends Serializable> {
	
	protected EntityManager entityManager;
	
	private Class<T> persistedClass;
	
	public void salvar(T entity) throws RepositoryException {
		try {
			if (entity.getId() != null) {
				atualizar(entity);
			} else {
				entityManager = Uteis.jpaEntityManager();
				entityManager.persist(entity);
				entityManager.flush();
			}
		} catch (Exception e) {
			tratarExcessaoRepositorio(e);
		}
	}

	private void atualizar(T entity) throws RepositoryException {
		try {
			entityManager = Uteis.jpaEntityManager();
			T find = (T) entityManager.find(entity.getClass(), entity.getId());
			atualizarModelo(find, entity);
			entityManager.merge(find);
		} catch (Exception e) {
			tratarExcessaoRepositorio(e);
		}
	}

	protected void atualizarModelo(IEntity find, IEntity novo) {
		try {
			BeanUtils.copyProperties(find, novo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Class classe, I id) throws RepositoryException {
		try {
			entityManager = Uteis.jpaEntityManager();
			T find = (T) entityManager.find(classe, id);
			if (find != null) {
				entityManager.remove(find);
				entityManager.flush();
			}
		} catch (Exception e) {
			tratarExcessaoRepositorio(e);
		}
	}

	public T buscar(Long id) throws RepositoryException {
		try {
			entityManager = Uteis.jpaEntityManager();
			T find = (T) entityManager.find(persistedClass, id);
			if (find != null) 
				return find;
		} catch (Exception e) {
			tratarExcessaoRepositorio(e);
		}
		return null;
	}

	protected abstract Class getClasse();

	protected void tratarExcessaoRepositorio(Exception e) throws RepositoryException {
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

}
