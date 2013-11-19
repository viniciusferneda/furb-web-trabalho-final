package br.finf.filter;

public enum Type {
	
	EQUALS(" = %s "),
	LIKE(" like %s "),
	NOT_EQUALS(" != %s "),
	NOT_LIKE(" not like %s "),
	IN(" in ( %s ) ");

	private String expression;
	
	private Type(String expression) {
		this.expression = expression;
	}
	
	public String getExpression() {
		return expression;
	}
}
