package br.finf.model.rule;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import br.finf.dao.BasicDAO;
import br.finf.dao.DBSession;
import br.finf.dao.entity.AbstractEntity;
import br.finf.filter.QueryBuilder;

/**
 * Classe base de negócio específica para tratar uma {@link AbstractEntity}.
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
	 * Evento que é chamado antes do objeto ser salvo na base de dados.
	 */
	protected void beforeSave(AbstractEntity entity) {
		if (entity == null) {
			throw new NullPointerException("Não é possível salvar registros nulos.");
		}
	}

	/**
	 * Objeto que é chamado após o objeto ser salvo na base de dados.
	 */
	protected void afterSave(AbstractEntity entity) {
	}
	
	/**
	 * Executa a {@link Query} padrão definida em T.
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
		
		return query.list();
	}
	
	/**
	 * Popula a {@link Query} com os parâmetros e executa.
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
	 * Popula a {@link Query} com os parâmetros e executa.
	 * 
	 * @param query {@link Query} que deve ser executada.
	 * @param params pametros que devem ser passados para a {@link Query}.
	 * 
	 * @return a lista de registro obtidos.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> executeNamedQueryList(Query query, Object... params){
		putParams(query, params);
		
		return query.list();
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
		int i = 0;
		for (Object obj : params) {
			if (obj.getClass() == Long.class){
				query.setLong(i, (Long) obj);
			}else if (obj.getClass() == Integer.class){
				query.setInteger(i, (Integer) obj);
			}else if (obj.getClass() == String.class){
				query.setString(i, (String) obj);
			}else if (obj.getClass() == Date.class){
				query.setDate(i, (Date) obj);
			}else if (obj.getClass() == Float.class){
				query.setFloat(i, (Float) obj);
			}else if (obj.getClass() == Double.class){
				query.setDouble(i, (Double) obj);
			}else if (obj.getClass() == Byte.class){
				query.setByte(i, (Byte) obj);
			}else if (obj.getClass() == Character.class){
				query.setCharacter(i, (Character) obj);
			}else{
				throw new IllegalArgumentException("A class " + obj.getClass() + " do parametro : " + obj + " não é válido!");
			}
			i++;
		}
	}

	/**
	 * Obtém o class de T baseado generics.
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
	 * Obtém uma DAO do pool.
	 * 
	 * @param clazz DAO que deve ser obtida. 
	 */
	protected BasicDAO getDAO(Class<? extends BasicDAO> clazz){
		return RuleProvider.get().getDAO(session, clazz);
	}
	
	/**
	 * Executa um {@link QueryBuilder} e obtém o resultado.
	 * 
	 * @param query deve ser executado.
	 * 
	 * @return a lista de T.
	 */
	@SuppressWarnings("unchecked")
	public List<T> executeQueryList(QueryBuilder query) {
		Query q = newQuery(query.buildQuery());
		
		putParams(q, query.getParams().toArray(CLEAR_OBJECT_ARRAY));
		
		return q.list();
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
