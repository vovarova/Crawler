package fine.project;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskExecutor {
	private ThreadPoolExecutor taskExecutor;
	private Thread monitorThread;
	Filter filter;

	public TaskExecutor() {
		this(5);
	}

	public void executeTask(Task task) {
		taskExecutor.execute(task);
		//System.out.println("TASKS " + taskExecutor.getActiveCount());
	}

	public TaskExecutor(int initialSize) {
		taskExecutor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(initialSize);

	}

	public void startMonitoring() {
		if(monitorThread==null){			
			monitorThread = new Thread(new Runnable() {
				public void run() {
					while (true) {
						try {
							Thread.sleep(5000);
							System.out.println("Getting active tasks "+taskExecutor.getActiveCount());
							if(taskExecutor.getActiveCount()==0){
								Thread.sleep(5000);
								System.out.println(filter.getLinksMap().entrySet().size());
								System.out.println(filter.getLinksMap().keySet().toString());
								System.out.println("TIME "+((System.currentTimeMillis()-App.stdate)/1000));
								taskExecutor.shutdown();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			monitorThread.setDaemon(true);
			monitorThread.start();
		}
	}

}
