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
		e.setCallback(new MyInterceptor());
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
	 */
	private static class MyInterceptor implements MethodInterceptor {
		
		public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
			
			
			AbstractFacade facade = (AbstractFacade) object;
			
			if (method.getName().equals("getBE") || method.getName().equals("getAS") || method.getName().equals("getDAO")) {
				return methodProxy.invokeSuper(object, args);
			} else {
				DBSession session = facade.getSession();
				
				//abre a conexao
				session.openEntityManager();
				System.out.println("Iniciou a sessao...");
				try {
					// Inicia uma transacao com o banco de dados.
					session.beginTransaction();
					System.out.println("Iniciou a transacao...");
					try {
						// invoca o metodo e executa.
						Object result = methodProxy.invokeSuper(object, args);
						
						// commita as alteracoes no banco de dados.
						session.commitTransaction();
						System.out.println("Comitou a sessão...");
						return result;
					} catch (Throwable t) {
						// caso ocorra algum erro durante o processamento, irá garantir que nenhum alteracao realizada permaneca no banco.
						session.rollcackTransaction();
						System.out.println("Rollback da sessão...");
						throw t;
					}
				} finally {
					session.closeEntityManager();
				}
			}
		}

	}

}
