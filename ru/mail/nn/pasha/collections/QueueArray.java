package ru.mail.nn.pasha.collections;

import java.util.Arrays;
import java.util.Iterator;

// Queue base on usually array
public class QueueArray<T> implements Iterable<T> {
    private T[] array;
    private int head;
    private int last;
    private int capacity;
    // ------
    public QueueArray(int capacity) {
        array= (T[]) new Object[capacity];
        this.capacity = capacity;
        last= -1;
        head= 0;
    }
    // ---
    public T add(T value){
        last++;
        if(last == array.length-1) resize();
        array[last]= value;
        return value;
    }
    public T poll(){
        if(head > last) throw new EmptyQueueException("on 'poll'");
        return array[head++];
    }
    public T seekHead(){
        if(head > last) throw new EmptyQueueException("on 'seekHead'");
        return array[head];
    }
    public T seekLast(){
        if(head > last) throw new EmptyQueueException("on 'seekHead'");
        return array[last];
    }
    public boolean isEmpty(){
        return head > last;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cur= head;
            @Override
            public boolean hasNext() {
                return cur <= last;
            }
            @Override
            public T next() {
                return array[cur];
            }
        };
    }
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(array, head, last+1));
    }
    // ---
    private void resize(){
        int newSize= last - head + 1;
        if(newSize > array.length/2) newSize*=2;
        if(newSize < capacity) newSize= capacity;
        T[] newArray= (T[]) new Object[newSize];
        for(int i= head; i<= last; i++){
            newArray[i-head]= array[i];
        }

        array= newArray;
        last= last - head;
        head= 0;
        //System.out.println("array.length= "+array.length+", "+"head= "+head+", "+"last= "+last);
    }
}
class EmptyQueueException extends RuntimeException{
    public EmptyQueueException(String message) {
        super(message);
    }
}
/* for testing
        QueueArray<Payload> payloads= new QueueArray<>(2);
        System.out.println(payloads.add(new Payload("Paul", 9)));
        System.out.println(payloads.add(new Payload("Faul", 19)));
        System.out.println(payloads.add(new Payload("Gaul", 29)));
        System.out.println("poll= "+payloads.poll());
        System.out.println("poll= "+payloads.poll());
        System.out.println("poll= "+payloads.poll());
        System.out.println(payloads.add(new Payload("Gaul", 29)));
        System.out.println(payloads.add(new Payload("Maul", 129)));
        System.out.println("poll= "+payloads.poll());
        System.out.println(payloads.add(new Payload("Gaul", 29)));
        System.out.println(payloads.add(new Payload("Maul", 129)));
        System.out.println(payloads);
// next class for testing Queue<T>
class Payload{
    String name;
    int age;
    // constructors
    public Payload(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //methods
    @Override
    public String toString() {
        return '{' + name + ", " +age + '}';
    }
}
 */
