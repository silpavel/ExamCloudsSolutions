package ru.mail.nn.pasha.collections;

import java.util.Arrays;
import java.util.Iterator;
// di-direction queue
public class QueueTwoDirect<T> implements Iterable{
    private T[] array;
    private int head;
    private int last;
    private int capacity;
    // ------
    public QueueTwoDirect(int capacity) {
        array= (T[]) new Object[capacity];
        this.capacity = capacity;
        head= capacity/2;
        last= head -1;
    }
    // ---
    public T addEnd(T value){
        if(last == array.length-1) resize();
        last++;
        array[last]= value;
        return value;
    }
    public T addBegin(T value) {
        if(head == 0) resize();
        head--;
        array[head]= value;
        return value;
    }
    public T pollBegin(){
        if(head > last) throw new EmptyQueueException("on 'poll'");
        return array[head++];
    }
    public T pollEnd(){
        if(head > last) throw new EmptyQueueException("on 'poll'");
        return array[last--];
    }
    public T seekBegin(){
        if(head > last) throw new EmptyQueueException("on 'seekHead'");
        return array[head];
    }
    public T seekEnd(){
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
        int newSize= last - head + 1;// 111
        if(newSize > array.length/3) newSize*=3;// 000 1111 000 -> 0000 1111 0000
        else newSize*=2;// 000 11 000 -> 00 11 00
        if(newSize < capacity) newSize= capacity;
        T[] newArray= (T[]) new Object[newSize];
        //combine centers of queue and newArray;
        int start= newSize/2 - (last-head)/2;
        for(int i= head; i<=last ; i++){
            newArray[start+i]= array[i];
        }
        array= newArray;
        last= start+ (last-head);
        head= start;
        System.out.println("array.length= "+array.length+", "+"head= "+head+", "+"last= "+last);
    }
}


