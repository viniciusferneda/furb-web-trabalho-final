package br.finf.batch;

/**
 * Representação dos estados do pool de trabalhos em lote.
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
