package br.finf.control.facade;

import java.lang.reflect.Constructor;

import br.finf.dao.BasicDAO;
import br.finf.dao.DBSession;
import br.finf.dao.entity.AbstractEntity;
import br.finf.model.rule.BasicAS;
import br.finf.model.rule.BasicBE;

/**
 * Implementa��o base para a camada que obt�m os BEs, ASs, DAOs com a sess�o.

 * @author teixeira
 */
public abstract class AbstractFacade {
	
	private final DBSession session;
	
	public AbstractFacade(DBSession session) {
		this.session = session;
	}
	
	/**
	 * Obt�m {@link BasicAS} inst�ncia de AS correspondente.
	 * @param clazz classe desejada.
	 * 
	 * @return a int�ncia obtida.
	 */
	protected BasicAS getAS(Class<? extends BasicAS> clazz) {
		return (BasicAS) create(clazz);
	}

	/**
	 * Obt�m {@link BasicBE} inst�ncia de AS correspondente.
	 * @param clazz classe desejada.
	 * 
	 * @return a int�ncia obtida.
	 */
	@SuppressWarnings("unchecked")
	protected <T extends BasicBE<? extends AbstractEntity>> T getBE(Class<T> clazz) {
		Object instance = create(clazz);
		return (T) instance;
	}
	
	/**
	 * Obt�m {@link BasicDAO} inst�ncia de AS correspondente.
	 * @param clazz classe desejada.
	 * 
	 * @return a int�ncia obtida.
	 */
	protected BasicDAO getDAO(Class<? extends BasicDAO> clazz) {
		Object instance = create(clazz);
		return (BasicDAO) instance;
	}
	
	/**
	 * Instancia a classe de regra com o interceptador de chamadas.
	 * @param rule
	 * @return
	 */
	private Object create(Class<?> rule) {
		try {
			Constructor<?> constructor = rule.getConstructor(DBSession.class);
			return constructor.newInstance(session);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("N�o foi poss�vel criar a classe " + rule.getCanonicalName(), e);
		}
	}


}
