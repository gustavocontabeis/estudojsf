package br.com.codersistemas.model.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class PessoaRepositoryTest {
	
	private EntityManagerFactory entityManagerFactory;
	 
	private String persistence_unit_name = "unit_app2";

	@Test
	public void testBuscar() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name); 
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		org.h2.Driver dr;
		System.out.println("xxxxx");
	}

}
