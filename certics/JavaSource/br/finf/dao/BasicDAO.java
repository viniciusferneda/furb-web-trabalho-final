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
	 * Representa a sessão com o Banco de dados via hibernate.
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
	 * Constrói uma {@link Query}.
	 * 
	 * @param query consulta que deseja-se executar.
	 * @return a instâcia obtida.
	 */
	protected Query newQuery(String query) {
		return session.newQuery(query);
	}
	
	/**
	 * Constrói uma {@link Query} baseado em uma consulta pré-definida na {@link AbstractEntity}.
	 * 
	 * @param entity entidade onde a query está definida.
	 * @param name nome da query que deve ser cosntruída.
	 * 
	 * @return a instâcia obtida.
	 */
	protected Query loadQuery(Class<? extends AbstractEntity> entity, String name){
		return session.loadQuery(entity, name);
	}
	
}
