package br.finf.filter.simple;

import br.finf.filter.Type;
import br.finf.filter.join.Join;
import br.finf.filter.value.FValue;

public class OrFilter extends Filter {
	
	public OrFilter(Join join, Type type, String field, FValue value) {
		super(join, type, field, value);
	}
	
	@Override
	public String getExpression() {
		return " or " + super.getExpression();
	}

}
