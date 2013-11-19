package br.finf.filter.join;

import br.finf.dao.entity.AbstractEntity;

public class Join {

	private final Class<? extends AbstractEntity> from;

	private final String alias;

	public Join(Class<? extends AbstractEntity> from, String alias) {
		this.from = from;
		this.alias = alias;
	}

	public Class<? extends AbstractEntity> getFrom() {
		return from;
	}

	public String getAlias() {
		return alias;
	}

}
