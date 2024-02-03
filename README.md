# ThreadSchedulerJava
The project simulates a thread scheduler; the tasks
arrive in the system, in a dispatcher, which has the role to
send the tasks to the nodes, based on some policies.
There are various planning policies;
I have 4 files:
1) MyDispatcher
2) MyHost
3) TimerForHosts
4) MyComparator

1) MyDispatcher: it deals with the delivery of tasks
to the corresponding nodes. To send a task,
addTask is called.
a) RR: as the tasks arrive, they are assigned to the node (id_
last_node + 1) % total_node_number.
b) SQ: the task is assigned to the node with the task queue of
minimum size.
c) SITA: tasks are of 3 types: SHORT, MEDIUM, LONG.
SHORT is for node 0, MEDIUM is for node 1, LONG is
for node 2.
d) LWL: it's like SQ, but getWorkLeft is taken into account instead of
tail size

2) MyHost: has the following functions:
a) addTask: add a task to the task queue with
priorities;
b) getQueueSize: the length of the queue is returned; if it
works on a task, then 1 is added to the answer, for
the task that is being worked on at that moment.
c) getWorkLeft: represents the remaining execution time for
tasks
d) shutdown: stops the execution of the node when
the size of the queue is 0 (all tasks have been executed).
e) run: in a while true, it is checked if it still exists
tasks in the queue; if yes, they are started and executed. In
the moment when they finish their execution, they are marked as
completed and then removed from the queue.

3) TimerForHosts: this represents a timer like that
implemented in the checker, which measures how much time has passed
from its initialization

4) MyComparator: this is the comparator used
to create the queue; the tasks are sorted in this way
according to priority in descending order, and if
2 tasks have the same priority, they are ordered in ascending order
depending on the time at which they started their execution
