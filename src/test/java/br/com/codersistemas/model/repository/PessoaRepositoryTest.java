package br.com.codersistemas.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;

import br.com.codersistemas.model.entity.Pessoa;
import br.com.codersistemas.model.entity.Usuario;

public class PessoaRepositoryTest {
	
	private EntityManagerFactory entityManagerFactory;
	 
	private String persistence_unit_name = "unit_app2";

	private EntityManager entityManager;
	
	@Before
	public void testAntes() {
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name); 
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
		inserir();
	}
	

	@Test
	public void testSelect() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Pessoa> query = criteriaBuilder.createQuery(Pessoa.class);
		Root<Pessoa> from = query.from(Pessoa.class);
		
		query.where(
				criteriaBuilder.and(
						criteriaBuilder.gt(from.get("id"), 1),									// > 
						criteriaBuilder.lt(from.get("id"), 1000), 								// <
						criteriaBuilder.equal(from.get("cpf"), 1235678900)),					// =
						criteriaBuilder.not(criteriaBuilder.equal(from.get("cpf"), 1235678900))	// <>
				);
		
		CriteriaQuery<Pessoa> select = query.select(from);

		List<Pessoa> resultList = entityManager.createQuery(select).getResultList();
		
		for (Pessoa pessoa : resultList) {
			System.out.println(pessoa);
		}
		
	}

	@Test
	public void testCount() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<Pessoa> from = query.from(Pessoa.class);
		
		query.where(
				criteriaBuilder.and(
						criteriaBuilder.gt(from.get("id"), 1),									// > 
						criteriaBuilder.lt(from.get("id"), 1000), 								// <
						criteriaBuilder.equal(from.get("cpf"), 1235678900)),					// =
						criteriaBuilder.not(criteriaBuilder.equal(from.get("cpf"), 1235678900))	// <>
				);
		
		CriteriaQuery<Long> select = query.select(criteriaBuilder.count(from));

		Long count = entityManager.createQuery(select).getSingleResult();
		
		System.out.println(count);
		
	}

	@Test
	public void testPaginadoECount() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Pessoa> query = criteriaBuilder.createQuery(Pessoa.class);
		Root<Pessoa> from = query.from(Pessoa.class);
		
		query.where(
				criteriaBuilder.and(
						criteriaBuilder.gt(from.get("id"), 1), 
						criteriaBuilder.lt(from.get("id"), 1000))
				);
		
		CriteriaQuery<Pessoa> select = query.select(from);

		List<Pessoa> resultList = entityManager.createQuery(select).setFirstResult(0).setMaxResults(5).getResultList();
		
		for (Pessoa pessoa : resultList) {
			System.out.println(pessoa);
		}
		
		CriteriaBuilder criteriaBuilderCount = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Long> queryCount = criteriaBuilderCount.createQuery(Long.class);
		Root<Pessoa> fromCount = queryCount.from(Pessoa.class);
		
		CriteriaQuery<Long> selectCount = queryCount.select(criteriaBuilder.count(fromCount));

		Long count = entityManager.createQuery(selectCount).getSingleResult();
		
		System.out.println(count);
		
	}


	private void inserir() {
		for (int i = 0; i < 20; i++) {
			System.out.println("----------");
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			Pessoa p = new Pessoa();
			p.setCpf("123567890"+i);
			p.setDataCadastro(LocalDateTime.now());
			p.setEmail("usuario"+i+"@email.com");
			p.setEndereco("endereço "+i);
			p.setExclusao(null);
			p.setId(null);
			p.setNome("Nome "+i);
			p.setOrigemCadastro("I");
			p.setSexo("M");
			p.setUsuario(new Usuario());
			p.getUsuario().setId(null);
			p.getUsuario().setExclusao(null);
			p.getUsuario().setLogin("login"+i);
			p.getUsuario().setSenha("senha"+i);
			entityManager.persist(p);
			entityManager.flush();
			transaction.commit();
			System.out.println(p);
		}
	}

}
