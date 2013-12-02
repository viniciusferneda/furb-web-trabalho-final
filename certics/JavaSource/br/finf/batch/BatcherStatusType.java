package br.finf.batch;

/**
 * Representa��o dos estados do pool de trabalhos em lote.
 * 
 */
public enum BatcherStatusType {

	/**
	 * Sendo populado.
	 */
	PREPARING,
	/**
	 * Exeutando.
	 */
	EXECUTING,
	/**
	 * Completo.
	 */
	COMPLETED;

}
