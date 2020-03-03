package ru.mail.nn.pasha.collections;
//priority is int number, it have uniq value
public class PriorityQueueT<T>{
    private ArrayListT<Priority<T>> queues;
    private int capacityOfInnerQueue;
    // --- constructors
    public PriorityQueueT(int capacityOfInnerQueue){
        this.queues = new ArrayListT<>();
        this.capacityOfInnerQueue= capacityOfInnerQueue;
    }
    // --- methods
    /** add value with priority*/
    public T add(T value, int priority){
        // find queue with certain priority
        boolean isAdded=false;// true if the value will be added during next loop
        for (int i = 0; i <queues.length() && !isAdded ; i++) {
            //get priority of queue from 'queues'
            int queuePriority= queues.get(i).priority;
            //add value to existing queue
            if(priority == queuePriority){
                queues.get(i).add(value);
                isAdded= true;}
            /* added value in new queue, priority the queue
             lies in range min and max priority of existing queues*/
            else if(priority < queuePriority){
                Priority<T> newQueue= new Priority<>(//make new queue
                        capacityOfInnerQueue, priority);
                newQueue.add(value);// put in the queue first value
                queues.add(newQueue, i);// add new queue to queues by index
                isAdded= true;
            }
        }
        // value have priority ot of range min/max
        if(!isAdded) {
            Priority<T> newQueue= new Priority<>(//make new queue
                    capacityOfInnerQueue, priority);
            newQueue.add(value);// put in the queue first value
            queues.add(newQueue);// add new queue to end queues
        };
        return value;
    }
    /** get value with max priority.
     * Warning! Queue with zero length (after poll) is not deleted from queues.
     */
    public T poll(){
        /* find not-zero-length queue with max priority
         is first in list not-zero-length queue because array 'queues' was sorted
         */
        for(Priority<T> queue: queues){
            if(!queue.isEmpty()){
                return queue.poll();
            }
        }
        // You here? This means that all queues are empty. It is exception.
        throw new EmptyQueueException("call 'poll': priority queue is empty");
    }
    /** get value from queue elems with certain priority
     *  and allow/not_allow exception if queue is empty*/
    public T poll(int priority, boolean allowException){
        for(Priority<T> queue: queues){
            /* if queue from queues is not empty and
             it priority equals certain priority
             */
            if(!queue.isEmpty() && queue.priority == priority)
                return queue.poll();// normal exit from poll(priority)
        }
        // You here if queue with certain priority is empty or absent
        if(allowException)// throw exception
            throw new EmptyQueueException("call 'poll' for empty queue");
        else // call poll for queue with max priority
            return poll();
    }
    /** get value from queue elems with certain priority and call poll() if queue is empty*/
    public T poll(int priority){
        return poll(priority, false);
    }
    /** check priority queue is empty*/
    public boolean isEmpty(){
        for(Priority<T> queue: queues){
            // check: one queue is not empty
            if(!queue.isEmpty()) return false;
        }
        // all queues were empty or array 'queues' have zero length
        return true;
    }
    /** check for elems with a specific priority*/
    public boolean isEmpty(int priority){
        for(Priority<T> queue: queues){
            // find queue with certain priority
            if(queue.priority == priority) return queue.isEmpty();
        }
        // You here if queue with certain priority not find i.e. is empty
        return true;
    }
    /** look head elem from priority queue*/
    public T peek(){
        /* find not-zero-length queue with max priority
         is first in list not-zero-length queue because array 'queues' was sorted
         */
        for(Priority<T> queue: queues){
            if(!queue.isEmpty()){
                return queue.seekHead();
            }
        }
        // You here? This means that all queues are empty. It is exception.
        throw new EmptyQueueException("call 'poll': priority queue is empty");
    }
    /** look head elem from elems with certain priority
     * and call peek() if such elems is absent*/
    public T peek(int priority){
        return peek(priority, false);
    }
    /** look head elem from elems with certain priority
     * and allow/not_allow exception if such elems is absent*/
    public T peek(int priority, boolean allowException){
        for(Priority<T> queue: queues){
            /* if queue from queues is not empty and
             it priority equals certain priority
             */
            if(!queue.isEmpty() && queue.priority == priority)
                return queue.seekHead();// normal exit from poll(priority)
        }
        // You here if queue with certain priority is empty or absent
        if(allowException)// throw exception
            throw new EmptyQueueException(
                    "call 'seek' for empty queue with certain priority");
        else // call seek for queue with max priority
            return peek();
    }
    @Override
    public String toString() {
        StringBuilder resault= new StringBuilder("[\n");
        for(Priority<T> queue: queues){
            if(!queue.isEmpty()) {// empty queue can to appear after call 'poll()'
                resault.append(queue);// call toString for Priority
                resault.append('\n');
            }
        }
        resault.append("]");
        return resault.toString();
    }
    // --- other
    private class Priority<T> extends QueueArray<T>{
        int priority;
        //
        public Priority(int capacity, int priority) {
            super(capacity);
            this.priority = priority;
        }
        @Override
        public String toString() {
            return "< priority= " + priority + ", " +
                    super.toString() +
                    " >";
        }
    }
}
