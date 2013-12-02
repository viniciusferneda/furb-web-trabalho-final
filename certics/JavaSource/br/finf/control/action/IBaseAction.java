package br.finf.control.action;

/**
 * Representação de nível abstrato das ações de uma action.
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
