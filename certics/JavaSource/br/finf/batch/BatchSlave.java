package br.finf.batch;

/**
 * Thread escreva que fica obtendo e executando trabalhos de um pool.
 * 
 * @author teixeira
 */
class BatchSlave extends Thread {

	private final BatchPool pool;

	public BatchSlave(BatchPool pool) {
		this.pool = pool;
	}

	@Override
	public void run() {
		IPack pack = pool.getPack();
		
		while (pack != null) {
			pack.execute();
			
			pack = pool.getPack();
		}
	}

}
