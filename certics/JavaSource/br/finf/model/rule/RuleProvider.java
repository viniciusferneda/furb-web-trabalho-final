package br.finf.model.rule;

import java.lang.reflect.Constructor;

import br.finf.dao.BasicDAO;
import br.finf.dao.DBSession;
import br.finf.dao.entity.AbstractEntity;

/**
 * Classe que prov� {@link BasicDAO} e {@link BasicBE} com a session mantida.
 * 
 * @author teixeira
 */
public class RuleProvider {
	
	private static final RuleProvider INSTANCE  = new RuleProvider();
	
	/**
	 * Obt�m a �nica instancia.
	 * 
	 * @return a �nica instancia.
	 */
	public static RuleProvider get() {
		return INSTANCE;
	}
	
	/**
	 * Obt�m uma inst�ncia de {@link BasicDAO} correspond�nte.
	 * 
	 * @param session sess�o sob a qual a dao ir� trabalhar.
	 * @param clazz {@link BasicDAO} desejada.
	 * 
	 * @return a inst�ncia obtida.
	 */
	BasicDAO getDAO(DBSession session, Class<? extends BasicDAO> clazz){
		try {
			@SuppressWarnings("unchecked")
			Constructor<BasicDAO> constructor = (Constructor<BasicDAO>) clazz.getConstructor(session.getClass());
			return constructor.newInstance(session);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid constructor for create DAO with session.", e);
		}
	}

	/**
	 * Obt�m uma inst�ncia de {@link BasicBE} correspond�nte.
	 * 
	 * @param session sess�o sob a qual a be ir� trabalhar.
	 * @param clazz {@link BasicBE} desejada.
	 * 
	 * @return a inst�ncia obtida.
	 */
	<T extends BasicBE<? extends AbstractEntity>> T getBE(DBSession session, Class<T> clazz){
		try {
			Constructor<T> constructor = clazz.getConstructor(session.getClass());
			return constructor.newInstance(session);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid constructor for create BE with session.", e);
		}
	}
	
}
