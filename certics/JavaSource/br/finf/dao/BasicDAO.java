package br.finf.dao;

import org.hibernate.Query;

import br.finf.dao.entity.AbstractEntity;

/**
 * Classe base para uso de DAO.
 * 
 * @author teixeira
 */
public class BasicDAO {
	
	/**
	 * Representa a sess�o com o Banco de dados via hibernate.
	 */
	private DBSession session;

	public BasicDAO(DBSession session){
		if (session == null){
			throw new IllegalArgumentException("The Session can't be null.");
		}
		this.session = session;
	}

	/**
	 * Salva o registro na base de dados.
	 */
	protected void save(AbstractEntity entity) {
		session.save(entity);
	}
	
	/**
	 * Constr�i uma {@link Query}.
	 * 
	 * @param query consulta que deseja-se executar.
	 * @return a inst�cia obtida.
	 */
	protected Query newQuery(String query) {
		return session.newQuery(query);
	}
	
	/**
	 * Constr�i uma {@link Query} baseado em uma consulta pr�-definida na {@link AbstractEntity}.
	 * 
	 * @param entity entidade onde a query est� definida.
	 * @param name nome da query que deve ser cosntru�da.
	 * 
	 * @return a inst�cia obtida.
	 */
	protected Query loadQuery(Class<? extends AbstractEntity> entity, String name){
		return session.loadQuery(entity, name);
	}
	
}
