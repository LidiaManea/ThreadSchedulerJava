import java.util.Comparator;
public class MyComparator<T> implements Comparator<Task> {
    public int compare(Task first, Task second) {
                        if (first.getPriority() > second.getPriority()) {
                            return -1;
                        } else if (first.getPriority() < second.getPriority()) {
                            return 1;
                        } 
                        if (first.getStart() < second.getStart()) {
                            return -1;
                        } else if (first.getStart() > second.getStart()) {
                            return 1;
                        }
                        return 0;
}
}
