package br.xereta.model.enums;

public enum SexoEnum {

	/* Masculino */
	MASCULINO("Masculino"),
	/* Feminino */
	FEMININO("Feminino");

	private String descricao;

	private SexoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
