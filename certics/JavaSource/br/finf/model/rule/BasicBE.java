package br.finf.model.rule;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Query;

import br.finf.dao.BasicDAO;
import br.finf.dao.DBSession;
import br.finf.dao.entity.AbstractEntity;
import br.finf.filter.QueryBuilder;

/**
 * Classe base de neg�cio espec�fica para tratar uma {@link AbstractEntity}.
 * 
 * @author teixeira
 *
 * @param <T> {@link AbstractEntity} que deve ser manipulada.
 */
public class BasicBE<T extends AbstractEntity> extends BasicDAO {
	
	private static final Object[] CLEAR_OBJECT_ARRAY = new Object[]{};
	private final DBSession session;
	
	public BasicBE(DBSession session) {
		super(session);
		this.session = session;
	}

	private Class<T> genericType = null;

	/**
	 * Salva o {@link AbstractEntity} na base.
	 */
	@Override
	public void save(AbstractEntity entity) {
		beforeSave(entity);
		super.save(entity);
		afterSave(entity);
	}

	/**
	 * Evento que � chamado antes do objeto ser salvo na base de dados.
	 */
	protected void beforeSave(AbstractEntity entity) {
		if (entity == null) {
			throw new NullPointerException("N�o � poss�vel salvar registros nulos.");
		}
	}

	/**
	 * Objeto que � chamado ap�s o objeto ser salvo na base de dados.
	 */
	protected void afterSave(AbstractEntity entity) {
	}
	
	/**
	 * Executa a {@link Query} padr�o definida em T.
	 * 
	 * @return os registros obtidos.
	 */
	public List<T> getAll(){
		return executeNamedQueryList("selectAll", CLEAR_OBJECT_ARRAY);
	}

	/**
	 * Executa uma {@link Query} nomeada no {@link AbstractEntity}.
	 * 
	 * @param name nome da {@link Query} que deve ser executada.
	 * @param params pametros que devem ser passados para a {@link Query}.
	 * 
	 * @return a lista de registro obtidos.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> executeNamedQueryList(String name, Object... params){
		Query query = loadQuery(name);
		
		putParams(query, params);
		
		return query.getResultList();
	}
	
	/**
	 * Popula a {@link Query} com os par�metros e executa.
	 * 
	 * @param query {@link Query} que deve ser executada.
	 * @param params pametros que devem ser passados para a {@link Query}.
	 * 
	 * @return a lista de registro obtidos.
	 */
	protected List<T> executeNamedQueryList(Query query, List<Object> params){
		return executeNamedQueryList(query, params.toArray(CLEAR_OBJECT_ARRAY));
	}
	
	/**
	 * Popula a {@link Query} com os par�metros e executa.
	 * 
	 * @param query {@link Query} que deve ser executada.
	 * @param params pametros que devem ser passados para a {@link Query}.
	 * 
	 * @return a lista de registro obtidos.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> executeNamedQueryList(Query query, Object... params){
		putParams(query, params);
		
		return query.getResultList();
	}
	
	/**
	 * Executa uma {@link Query} nomeada no {@link AbstractEntity}.
	 * 
	 * @param name nome da {@link Query} que deve ser executada.
	 * @param params pametros que devem ser passados para a {@link Query}.
	 * 
	 * @return o primeiro registro encontrado.
	 */
	protected T executeNamedQuery(String name, Object... params){
		List<T> list = executeNamedQueryList(name, params);
		
		if (list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * Executa uma {@link Query} nomeada no {@link AbstractEntity}.
	 *  
	 * @param name nome da {@link Query} que deve ser executada.
	 * @param pametros que devem ser passados para a {@link Query}.
	 * 
	 * @return a lista de registros obtidos.
	 */
	protected int updateNamedQuery(String name, Object[] params) {
		Query query = loadQuery(name);
		
		putParams(query, params);
		
		return query.executeUpdate();
	}

	protected void putParams(Query query, Object[] params) {
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
	}

	/**
	 * Obt�m o class de T baseado generics.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Class<T> g() {
		if (genericType == null) {
			ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
			genericType = (Class<T>) superclass.getActualTypeArguments()[0];
		}
		return genericType;
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
	 * Executa um {@link QueryBuilder} e obt�m o resultado.
	 * 
	 * @param query deve ser executado.
	 * 
	 * @return a lista de T.
	 */
	@SuppressWarnings("unchecked")
	public List<T> executeQueryList(QueryBuilder query) {
		Query q = newQuery(query.buildQuery());
		
		putParams(q, query.getParams().toArray(CLEAR_OBJECT_ARRAY));
		
		return q.getResultList();
	}
	
	/**
	 * Carrega a {@link Query} para a entidade T.
	 * 
	 * @param name nome da query.
	 * 
	 * @return a {@link Query} para a entidade T.
	 */
	protected Query loadQuery(String name) {
		return loadQuery(g(), name);
	}

}
