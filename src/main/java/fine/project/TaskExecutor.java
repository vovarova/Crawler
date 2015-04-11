package fine.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor {
	private ExecutorService taskExecutor;

	public TaskExecutor() {
		this(5);
	}
	public void executeTask(Task task){
		taskExecutor.execute(task);
	}

	public TaskExecutor(int initialSize) {
		taskExecutor = Executors.newFixedThreadPool(initialSize);
	}

}
