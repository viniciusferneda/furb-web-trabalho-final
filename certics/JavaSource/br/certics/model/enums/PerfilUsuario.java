package br.certics.model.enums;

public enum PerfilUsuario {

	ADM("Administrador"),
	AVALIADOR("Avaliador");
	
	private String descricao;

	private PerfilUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
