package br.finf.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.finf.dao.entity.AbstractEntity;

/**
 * Classe que mant�m a sess�o com o banco de dados para o Hibernate.
 * 
 */
public class DBSession {

	private EntityManager entityManager;

	DBSession() {
	}
	
	private EntityManager getEntityManager() {
		if (entityManager == null){
			throw new IllegalStateException("Sess�o n�o iniciada!");
		}
		return entityManager;
	}

	public void openEntityManager() {
		if(entityManager != null) {
			throw new IllegalStateException("Entity Manager j� iniciado!");
		}
		entityManager = HibernateUtil.getEntityManager("certics");
	}
	
	public void beginTransaction() {
		if(entityManager.getTransaction().isActive()) {
			throw new IllegalStateException("Transa��o j� iniciada!");
		}
		entityManager.getTransaction().begin();
	}

	public void commitTransaction() {
		if(!entityManager.getTransaction().isActive()) {
			throw new IllegalStateException("N�o possui transa��o ativa!");
		}
		entityManager.getTransaction().commit();
	}

	public void rollcackTransaction() {
		if(!entityManager.getTransaction().isActive()) {
			throw new IllegalStateException("N�o possui transa��o ativa!");
		}
		entityManager.getTransaction().rollback();
	}
	
	public void closeEntityManager() {
		if(entityManager == null) {
			throw new IllegalStateException("N�o possui Entity Manager iniciado!");
		}
		entityManager.close();
		entityManager = null;
	}

	public void save(AbstractEntity entity) {
		getEntityManager().persist(entity);
	}

	protected Query newQuery(String query) {
		return getEntityManager().createQuery(query);
	}
	
	protected Query loadQuery(Class<? extends AbstractEntity> entity, String name){
		return getEntityManager().createNamedQuery(entity.getSimpleName() + "." + name);
	}

}
