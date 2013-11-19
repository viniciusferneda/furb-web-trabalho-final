package br.finf.model.rule;

import br.finf.dao.BasicDAO;
import br.finf.dao.DBSession;
import br.finf.dao.entity.AbstractEntity;

/**
 * Classe base para regras de neg�cios n�o espec�ficas de objeto.
 * 
 * @author teixeira
 */
public class BasicAS extends BasicDAO {
	
	private final DBSession session;

	public BasicAS(DBSession session) {
		super(session);
		this.session = session;
	}
	
	/**
	 * Obt�m uma DAO do pool.
	 * 
	 * @param clazz DAO que deve ser obtida. 
	 */
	protected BasicDAO getDAO(Class<? extends BasicDAO> clazz){
		return RuleProvider.get().getDAO(session, clazz);
	}
	
	/**
	 * Obt�m uma BE do pool.
	 * 
	 * @param clazz BE que deve ser obtida. 
	 */
	protected <T extends BasicBE<? extends AbstractEntity>> T getBE(Class<T> clazz){
		return RuleProvider.get().getBE(session, clazz);
	}
	
}
