/* Implement this class. */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class MyDispatcher extends Dispatcher {
    int last;
    ArrayList<Double> times;
    ArrayList<Integer> lengths;
    public MyDispatcher(SchedulingAlgorithm algorithm, List<Host> hosts) {
        super(algorithm, hosts);
        last = -1;
        times = new ArrayList<Double>(hosts.size());
        lengths = new ArrayList<Integer>(hosts.size());
    }

    @Override
    public synchronized void addTask(Task task) {
        if (algorithm == SchedulingAlgorithm.ROUND_ROBIN) {
            int x = (last + 1) % hosts.size();
            hosts.get(x).addTask(task);
            last = x;
        } else if (algorithm == SchedulingAlgorithm.SHORTEST_QUEUE) {
            lengths.clear();
            for (int i = 0; i < hosts.size(); i++) {
                lengths.add(hosts.get(i).getQueueSize());
            }
            double result = lengths.get(0);
            int id = 0;
            for (int i = 1; i < hosts.size(); i++) {
                if (lengths.get(i) < result) {
                    result = lengths.get(i);
                    id = i;
                }
            }
            hosts.get(id).addTask(task);

        } else if (algorithm == SchedulingAlgorithm.SIZE_INTERVAL_TASK_ASSIGNMENT) {
            if (task.getType() == TaskType.SHORT) {
                hosts.get(0).addTask(task);
            } else if (task.getType() == TaskType.MEDIUM) {
                hosts.get(1).addTask(task);
            } else if (task.getType() == TaskType.LONG) {
                hosts.get(2).addTask(task);
            }

        } else if (algorithm == SchedulingAlgorithm.LEAST_WORK_LEFT) {
            
            double result = Math.round((double) hosts.get(0).getWorkLeft() / 1000);
            int id = 0;
            for (int i = 1; i < hosts.size(); i++) {
                if (Math.round((double) hosts.get(i).getWorkLeft() / 1000) < result) {
                    result = Math.round((double) hosts.get(i).getWorkLeft() / 1000);
                    id = i;
                }
            }
            
            hosts.get(id).addTask(task);
        }
        
    }
}
