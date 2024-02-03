/* Implement this class. */
import java.util.concurrent.PriorityBlockingQueue; 
import java.util.Comparator;
import java.util.*; 
import java.io.FileWriter;
import java.io.IOException;

public class MyHost extends Host {
    int working = 0;
    int shutdown = 0;
    //public Comparator<Task> tasksCompare = (Comparator.comparingInt(Task::getPriority).reversed()).thenComparingInt(Task::getStart);
    TimerForHosts timer = new TimerForHosts();
    PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<Task>( 
                1000, new MyComparator<Task>());
    
    @Override
    public void run() {
        while (true) {
                TimerForHosts timer = new TimerForHosts();
                if (getQueueSize() != 0) {
                        Task task = queue.peek();
                        timer.init();
                        while (true) {
                           working = 1;
                            if (timer.time_passed() >= task.getDuration()) {
                                task.finish();
                                queue.remove(task);
                                working = 0;
                                break; 
                            }
                        }
                    } 
                if (shutdown == 1) return; 
            }   
        }
    
    
    @Override
    public void addTask(Task task) {
        queue.add(task);
    }

    @Override
    public int getQueueSize() {
        if (working == 1) {
            return queue.size() + 1;
        }
        return queue.size();
    }

    @Override
    public long getWorkLeft() {
        long result = 0;
        for (Task task: queue) {
            result += task.getLeft();
        }
        return result;
    }

    @Override
    public void shutdown() {
        if (getQueueSize() == 0) {
            shutdown = 1;
        }
        return;
    }
}
