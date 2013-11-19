package br.finf.batch;

/**
 * Pacote de trabalho que deve ser executado.<br>
 * Ser� executado uma �nica vez pelo pool.
 * 
 * @see BatchPool#process(boolean)
 * @author teixeira
 */
public interface IPack {

	/**
	 * Executa esse pacote de trabalho.
	 */
	void execute();

}
