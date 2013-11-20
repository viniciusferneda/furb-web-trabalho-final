package br.finf.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;

/**
 * Classe utilitária para obter a {@link SessionFactory} do hibernate.
 * 
 */
public class HibernateUtil {

	private static EntityManagerFactory entityManagerFactory;

	public static EntityManager getEntityManager(String dsName) {

		if ((entityManagerFactory == null) || !entityManagerFactory.isOpen()) {
			try {
				entityManagerFactory = Persistence.createEntityManagerFactory(dsName);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		return entityManagerFactory.createEntityManager();
	}

}