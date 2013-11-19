package br.finf.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.finf.dao.entity.AbstractEntity;
import br.finf.filter.group.GroupFilter;
import br.finf.filter.join.Join;
import br.finf.filter.simple.AndFilter;
import br.finf.filter.simple.Filter;
import br.finf.filter.simple.OrFilter;
import br.finf.filter.value.FValue;
import br.finf.filter.value.FilterValue;

public class QueryBuilder {

	private final Join from;

	private final Map<String, Join> joins = new HashMap<String, Join>();

	private final List<SimpleFilter> filters = new ArrayList<SimpleFilter>();

	public QueryBuilder(Class<? extends AbstractEntity> from, String alias) {
		this.from = new Join(from, alias);
	}

	public Join getFrom() {
		return from;
	}

	public Join addJoin(Class<? extends AbstractEntity> from, String alias) {
		if (joins.containsKey(alias)) {
			throw new IllegalArgumentException("Alias " + alias + " já utilizado");
		}
		return joins.put(alias, new Join(from, alias));
	}

	public void addFilter(SimpleFilter filter) {
		filters.add(filter);
	}

	public AndFilter addAndFilter(String field, final Object value) {
		return addAndFilter(Type.EQUALS, field, value);
	}
	
	public AndFilter addAndFilter(Type type, String field, final Object value) {
		return addAndFilter(type, field, new FilterValue() { @Override public Object value() { return value; } });
	}
	
	public AndFilter addAndFilter(Type type, String field,FValue value) {
		return addAndFilter(from, type, field, value);
	}
	
	public AndFilter addAndFilter(Join join, Type type, String field,FValue value) {
		AndFilter f = new AndFilter(join, type, field, value);
		filters.add(f);
		return f;
	}

	public OrFilter addOrFilter(Join join, Type type, String field, FValue value) {
		OrFilter f = new OrFilter(join, type, field, value);
		filters.add(f);
		return f;
	}
	
	public String buildQuery() {
		StringBuilder sb = new StringBuilder("select ").append(from.getAlias()).append(" from ").append(from.getFrom().getCanonicalName()).append(" ").append(from.getAlias()).append(" ").append("\n");
		//joins
		if (joins.size() > 0) {
			for (Join j : joins.values()) {
				sb.append(" join ").append(j.getFrom().getCanonicalName()).append(" ").append(j.getAlias()).append("\n");
			}
		}
		//filtros
		if (filters.size() > 0) {
			sb.append("where \n 1 = 1 \n");
			for (SimpleFilter f : filters) {
				if (f.isValid()) {
					sb.append(f.getExpression()).append("\n");
				}
			}
		}
		return sb.toString();
	}
	
	public List<Object> getParams() {
		List<Object> params = new ArrayList<Object>();
		
		for (SimpleFilter sf : filters) {
			if (sf instanceof Filter) {
				Filter f = (Filter) sf;
				if (f.isValidParam()) {
					params.add(f.getParam());
				}
			} else if (sf instanceof GroupFilter) {
				params.addAll(((GroupFilter) sf).getParams());
			} 
			
		}
		return params;
	}

}
