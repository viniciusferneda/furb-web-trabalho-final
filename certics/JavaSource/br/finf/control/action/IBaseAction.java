package br.finf.control.action;

/**
 * Representa��o de n�vel abstrato das a��es de uma action.
 * 
 */
public interface IBaseAction {

	/**
	 * Evento chamado antes da tela ser inicializada.
	 */
	void onSetUp();

	/**
	 * Evento chamado apos a finalizacao da tela.
	 */
	void onTearDown();

}
