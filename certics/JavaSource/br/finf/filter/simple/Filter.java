package br.finf.filter.simple;

import br.finf.filter.SimpleFilter;
import br.finf.filter.Type;
import br.finf.filter.join.Join;
import br.finf.filter.value.FieldFilter;
import br.finf.filter.value.FilterValue;
import br.finf.filter.value.FValue;
import br.finf.filter.value.FiltersValue;

public abstract class Filter extends SimpleFilter {

	private Join join;
	private Type type;
	private String field;
	private FValue value;

	public Filter(Join join, Type type, String field, FValue value) {
		this.join = join;
		this.type = type;
		this.field = field;
		this.value = value;
	}

	@Override
	public String getExpression() {
		String expression = new StringBuilder(join.getAlias()).append(".").append(field).append(type.getExpression()).toString();
		if (value instanceof FieldFilter) {
			expression = String.format(expression, ((FieldFilter) value).getExpression());
		} else if (type == Type.IN) {
			expression = String.format(expression, inValues());
		} else {
			expression = String.format(expression, "?");
		}
		return expression;
	}
	
	private String inValues() {
		StringBuilder sb = new StringBuilder();
		Object[] values = ((FiltersValue) value).values();
		
		for (int i = 0; i < values.length; i++) {
			Object o = values[i];
			if (o instanceof String) {
				sb.append("\"");
				sb.append(((String) o).trim());
				sb.append("\"");
			}
			if ((i + 1) < values.length) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	@Override
	public boolean isValid() {
		if (value instanceof FiltersValue){
			return ((FiltersValue) value).values() != null && ((FiltersValue) value).values().length > 0;
		} else if (value instanceof FilterValue){
			return ((FilterValue) value).value() != null;
		} else if (value instanceof FieldFilter) {
			return true;
		}
		return false;
	}
	
	public boolean isValidParam() {
		return isValid() && type != Type.IN && !(value instanceof FieldFilter);
	}
	
	public Object getParam() {
		if (value instanceof FilterValue){
			return ((FilterValue) value).value();
		}
		return null;
	}

}
