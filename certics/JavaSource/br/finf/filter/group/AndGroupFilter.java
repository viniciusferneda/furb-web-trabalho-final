package br.finf.filter.group;

/**
 * Grupo de condicionais com OR.
 * 
 */
public class AndGroupFilter extends GroupFilter {
	
	@Override
	public String getExpression() {
		return " and " + super.getExpression();
	}

}
