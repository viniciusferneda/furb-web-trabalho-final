package br.certics.model.enums;

public enum EscalaPontuacao {

	SIM("Sim"),
	NAO("Não"),
	F("Completamente atendido"),
	L("Largamente atendido"),
	P("Parcialmente atendido"),
	N("Não atendido");
	
	private String descricao;

	private EscalaPontuacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
