package br.finf.filter.value;

import br.finf.filter.join.Join;

public class FieldFilter implements FValue {
	
	private Join join;
	private String field;

	public FieldFilter(Join join, String field) {
		this.join = join;
		this.field = field;
	}
	
	public String getExpression() {
		return join.getAlias() + "." + field;
	}

}
