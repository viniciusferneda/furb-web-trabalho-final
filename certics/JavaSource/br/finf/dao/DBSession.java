package br.finf.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import br.finf.dao.entity.AbstractEntity;

/**
 * Classe que mantém a sessão com o banco de dados para o Hibernate.
 * 
 * @author thiago.teixeira
 */
public class DBSession {

	private Session session;

	DBSession() {
	}
	
	private Session getSession() {
		if (session == null){
			throw new IllegalStateException("Sessão não iniciada!");
		}
		if (!session.isOpen()) {
			session.beginTransaction();
		}
		return session;
	}

	public void beginTransaction() {
		session = HibernateUtil.getSessionFactory().openSession();
		getSession().beginTransaction();
	}

	public void commitTransaction() {
		try {
			if (session != null && session.isOpen() && session.getTransaction().isActive()) {
				getSession().getTransaction().commit();
			}
		} finally {
			closeSession();
		}
	}

	public void rollcack() {
		try {
			getSession().getTransaction().rollback();
		} finally {
			closeSession();
		}
	}
	
	private void closeSession() {
		if (session != null && session.isOpen()) {
			session.close();
		}
		session = null;
	}

	public void save(AbstractEntity entity) {
		getSession().save(entity);
	}

	protected Query newQuery(String query) {
		return getSession().createQuery(query);
	}
	
	protected Query loadQuery(Class<? extends AbstractEntity> entity, String name){
		return getSession().getNamedQuery(entity.getSimpleName() + "." + name);
	}

}
