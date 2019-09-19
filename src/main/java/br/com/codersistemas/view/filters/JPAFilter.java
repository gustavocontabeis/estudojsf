package br.com.codersistemas.view.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
 
/***
 * ESSE FILTER VAI SER CHAMADO TODA VEZ QUE FOR REALIZADO 
 * UMA REQUISI��O PARA O FACES SERVLET.
 * */
@WebFilter(servletNames ={ "Faces Servlet" })
public class JPAFilter implements Filter {
 
 
	private EntityManagerFactory entityManagerFactory;
 
	private String persistence_unit_name = "unit_app";
 
    public JPAFilter() {
 
    }
 
	public void init(FilterConfig fConfig) throws ServletException {
		 
		/*CRIA O entityManagerFactory COM OS PAR�METROS DEFINIDOS NO persistence.xml*/
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name); 
	}
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
		/*CRIANDO UM ENTITYMANAGER*/
		EntityManager entityManager =  this.entityManagerFactory.createEntityManager();
 
		/*ADICIONANDO ELE NA REQUISI��O*/
		request.setAttribute("entityManager", entityManager);
 
		/*INICIANDO UMA TRANSA��O*/
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
 
		/*INICIANDO FACES SERVLET*/
		chain.doFilter(request, response);
 
		try {
 			/*SE N�O TIVER ERRO NA OPERA��O ELE EXECUTA O COMMIT*/
			transaction.commit();
 		} catch (Exception e) {
 			e.printStackTrace();
			/*SE TIVER ERRO NA OPERA��O � EXECUTADO O rollback*/
			transaction.rollback();
		} finally {
 
			/*DEPOIS DE DAR O COMMIT OU ROLLBACK ELE FINALIZA O entityManager*/
			entityManager.close();
		}
	}
 
	public void destroy() {
		this.entityManagerFactory.close();
	}
 
}