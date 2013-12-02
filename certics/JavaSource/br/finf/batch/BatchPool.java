package br.finf.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Classe que disponibiliza e controla um pool de execuções.
 * 
 */
public class BatchPool {
	
	private AtomicReference<BatcherStatusType> status = new AtomicReference<BatcherStatusType>();
	private int totolPack = 0;
	private final Queue<IPack> queue = new SynchronousQueue<IPack>();
	private final List<Thread> slaves = new ArrayList<Thread>();
	
	public BatchPool(int slavesCount) {
		status.set(BatcherStatusType.PREPARING);
		for (int i = 0; i < slavesCount; i++) {
			slaves.add(new BatchSlave(this));
		}
	}
	
	
	/**
	 * Adiciona um pacote de trabalho ao pool.
	 *  
	 * @param pack pacote.
	 */
	public void addPack(IPack pack){
		if (status.get() != BatcherStatusType.PREPARING){
			throw new IllegalStateException("The batch was executed, now is invalid.");	
		}
		queue.add(pack);
	}
	
	/**
	 * Inicializa os trabalhos do pool e aguarda o fim.
	 */
	public void process() {
		process(true);
	}
	
	/**
	 * Inicializa os trablaho do pool e não aguarda a execução.<br>
	 * 
	 * @see #getPackCount()
	 * @see #getTotolPack()
	 * @see #getStatus()
	 * 
	 * @param wait <code>true</code> se deve aguardar a execução.
	 */
	public synchronized void process(boolean wait) {
		if (status.get() == BatcherStatusType.EXECUTING){
			throw new IllegalStateException("The batch is executing now.");
		}else if (status.get() == BatcherStatusType.COMPLETED){
			throw new IllegalStateException("The batch was executed, now is invalid.");
		}
		
		status.set(BatcherStatusType.EXECUTING);
		totolPack = queue.size();
		
		for (Thread t : slaves) {
			t.start();
		}
		if (wait){
			waitExecute();
		}else{
			new Thread(){
				public void run() {
					waitExecute();
				};
			}.start();
		}
	}


	private void waitExecute() {
		while(slaves.size() > 0){
			ArrayList<Thread> list = new ArrayList<Thread>(slaves);
			for (Thread t : list) {
				if (t.isAlive()){
					slaves.remove(t);
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		status.set(BatcherStatusType.COMPLETED);
	}
	
	public BatcherStatusType getStatus() {
		return status.get();
	}
	
	public synchronized int getPackCount(){
		return queue.size();
	}
	
	public int getTotolPack() {
		return totolPack;
	}
	
	synchronized IPack getPack(){
		return queue.poll();
	}

}
