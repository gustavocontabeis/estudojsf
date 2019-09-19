package br.com.codersistemas.model.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.codersistemas.exceptions.RepositoryException;
import br.com.codersistemas.model.entity.Pessoa;
import br.com.codersistemas.model.entity.Usuario;
import br.com.codersistemas.uteis.Uteis;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	//@PersistenceContext(unitName = "unit_app")
	private EntityManager jpaEntityManager;

	public Usuario validaUsuario(Usuario usuario) {

		try {
			// Query query =
			// Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");

			if (jpaEntityManager == null) {
				jpaEntityManager = Uteis.jpaEntityManager();
			}

			// EntityManager jpaEntityManager = Uteis.JpaEntityManager();
			Query query = jpaEntityManager.createQuery(
					"select obj from br.com.codersistemas.model.entity.Usuario obj where obj.login = :usuario and obj.senha = :senha");

			query.setParameter("usuario", usuario.getLogin());
			query.setParameter("senha", usuario.getSenha());

			return (Usuario) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public Usuario buscar(Long id) throws RepositoryException {
		try {
			jpaEntityManager = Uteis.jpaEntityManager();
			Usuario find = jpaEntityManager.find(Usuario.class, id);
			if (find != null) 
				return find;
		} catch (Exception e) {
			//tratarExcessaoRepositorio(e);
		}
		return null;
	}

}
