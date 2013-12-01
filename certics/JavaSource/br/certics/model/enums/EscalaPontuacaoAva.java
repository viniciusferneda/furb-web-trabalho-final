package br.certics.model.enums;

public enum EscalaPontuacaoAva {

	S("Sim"),
	N("N�o");
	
	private String descricao;

	private EscalaPontuacaoAva(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
