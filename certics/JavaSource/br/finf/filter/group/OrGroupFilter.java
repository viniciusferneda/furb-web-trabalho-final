package br.finf.filter.group;

/**
 * Grupo de condicionais com OR.
 * 
 */
public class OrGroupFilter extends GroupFilter {
	
	@Override
	public String getExpression() {
		return " or " + super.getExpression();
	}

}
