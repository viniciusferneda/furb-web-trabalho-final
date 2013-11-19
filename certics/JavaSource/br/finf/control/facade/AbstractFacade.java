package br.finf.control.facade;

import java.lang.reflect.Constructor;

import br.finf.dao.BasicDAO;
import br.finf.dao.DBSession;
import br.finf.dao.entity.AbstractEntity;
import br.finf.model.rule.BasicAS;
import br.finf.model.rule.BasicBE;

/**
 * Implementação base para a camada que obtém os BEs, ASs, DAOs com a sessão.

 * @author teixeira
 */
public abstract class AbstractFacade {
	
	private final DBSession session;
	
	public AbstractFacade(DBSession session) {
		this.session = session;
	}
	
	/**
	 * Obtém {@link BasicAS} instância de AS correspondente.
	 * @param clazz classe desejada.
	 * 
	 * @return a intância obtida.
	 */
	protected BasicAS getAS(Class<? extends BasicAS> clazz) {
		return (BasicAS) create(clazz);
	}

	/**
	 * Obtém {@link BasicBE} instância de AS correspondente.
	 * @param clazz classe desejada.
	 * 
	 * @return a intância obtida.
	 */
	@SuppressWarnings("unchecked")
	protected <T extends BasicBE<? extends AbstractEntity>> T getBE(Class<T> clazz) {
		Object instance = create(clazz);
		return (T) instance;
	}
	
	/**
	 * Obtém {@link BasicDAO} instância de AS correspondente.
	 * @param clazz classe desejada.
	 * 
	 * @return a intância obtida.
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
			throw new RuntimeException("Não foi possível criar a classe " + rule.getCanonicalName(), e);
		}
	}


}
