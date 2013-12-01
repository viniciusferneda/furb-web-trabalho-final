package br.certics.model.enums;

public enum Sexo {

	/* Masculino */
	MASCULINO("Masculino"),
	/* Feminino */
	FEMININO("Feminino");

	private String descricao;

	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
