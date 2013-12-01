package br.certics.model.enums;

public enum EscalaPontuacaoRE {

	F("Completamente atendido"),
	L("Largamente atendido"),
	P("Parcialmente atendido"),
	N("Não atendido");
	
	private String descricao;

	private EscalaPontuacaoRE(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
