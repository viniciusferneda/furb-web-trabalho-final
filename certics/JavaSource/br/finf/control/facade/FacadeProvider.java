package br.finf.control.facade;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import br.finf.dao.DBSession;
import br.finf.dao.SessionProvider;

public class FacadeProvider {
	
	private final static FacadeProvider INSTANCE = new FacadeProvider();
	private final Map<Class<? extends AbstractFacade>, AbstractFacade> instances = new HashMap<Class<? extends AbstractFacade>, AbstractFacade>();
	
	private FacadeProvider() {
	}
	
	public static FacadeProvider get() {
		return INSTANCE;
	}

	/*public <T extends AbstractFacade> T provide(Class<T> clazz) {
		
		T inst = verifyInstance(clazz);
		if (inst != null) {
			return inst;
		}
		
		try {
			Constructor<T> constructor = clazz.getConstructor(DBSession.class);
			return constructor.newInstance(SessionProvider.get().provide());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("No such contructor with DBSession param.");
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("No such contructor with DBSession param.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Problem on execute contructor.");
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public <T extends AbstractFacade> T provide(Class<T> clazz) {
		return (T) create(clazz, SessionProvider.get().provide());
	}
	
	/**
	 * Instancia a classe de regra com o interceptador de chamadas.
	 * @param rule
	 * @return
	 */
	private Object create(Class<?> rule, DBSession session) {
		Enhancer e = new Enhancer();
		e.setSuperclass(rule);
		e.setCallback(new MyInterceptor(session));
		Object instance = e.create(new Class[] { DBSession.class }, new Object[] { session });
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends AbstractFacade> T verifyInstance(Class<T> clazz) {
		return (T) instances.get(clazz);
	}
	
	/**
	 * Classe interceptadora.
	 * 
	 * @author teixeira
	 */
	private static class MyInterceptor implements MethodInterceptor {
		
		private final DBSession session;

		public MyInterceptor(DBSession session) {
			this.session = session;
		}
		
		public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
			
			Object result = null;
			if (method.getName().equals("getBE") || method.getName().equals("getAS") || method.getName().equals("getDAO")) {
				result = methodProxy.invokeSuper(object, args);
			} else {
				// Inicia uma transacao com o banco de dados.
				session.beginTransaction();
				System.out.println("Iniciou a sessão...");
				try {
					// invoca o metodo e executa.
					result = methodProxy.invokeSuper(object, args);
					// commita as alteracoes no banco de dados.
					session.commitTransaction();
					System.out.println("Comitou a sessão...");
				} catch (Throwable t) {
					// caso ocorra algum erro durante o processamento, irá garantir
					// que nenhum alteracao realizada permaneca no banco.
					session.rollcack();
					System.out.println("Rollback da sessão...");
					throw t;
				}
			}
			return result;
		}

	}

}
