package br.finf.model.rule;

import java.lang.reflect.Constructor;

import br.finf.dao.BasicDAO;
import br.finf.dao.DBSession;
import br.finf.dao.entity.AbstractEntity;

/**
 * Classe que provê {@link BasicDAO} e {@link BasicBE} com a session mantida.
 * 
 * @author teixeira
 */
public class RuleProvider {
	
	private static final RuleProvider INSTANCE  = new RuleProvider();
	
	/**
	 * Obtém a única instancia.
	 * 
	 * @return a única instancia.
	 */
	public static RuleProvider get() {
		return INSTANCE;
	}
	
	/**
	 * Obtém uma instância de {@link BasicDAO} correspondênte.
	 * 
	 * @param session sessão sob a qual a dao irá trabalhar.
	 * @param clazz {@link BasicDAO} desejada.
	 * 
	 * @return a instância obtida.
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
	 * Obtém uma instância de {@link BasicBE} correspondênte.
	 * 
	 * @param session sessão sob a qual a be irá trabalhar.
	 * @param clazz {@link BasicBE} desejada.
	 * 
	 * @return a instância obtida.
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
