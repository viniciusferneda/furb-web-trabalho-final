package br.finf.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Classe utilitária para obter a {@link SessionFactory} do hibernate.
 * 
 * @author thiago.teixeira
 */
@SuppressWarnings("deprecation")
class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Initial SessionFactory creation failed.");
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * Obtém a session factory.
	 * 
	 * @return a session factory.
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}