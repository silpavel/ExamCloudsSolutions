package ru.mail.nn.pasha.second;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayListT<T> implements Iterable<T>{
    private T[] arr;
    private int pointer;
    //constructors
    public ArrayListT(){
        this(16);
    }
    public ArrayListT(int size){
        arr= (T[]) new Object[size];
        pointer=-1;
    }
    // methods
    /** add to end*/
    public T add(T value){
        if(pointer == arr.length-1)
            upSizeArray();
        arr[++pointer]= value;
        return value;
    }
    /** add to index position*/
    public T add(T value, int index){
        checkIndex(index, "on 'add' by index");
        if(pointer == arr.length-1)
            upSizeArray();
        for (int i = pointer; i >=index ; i--) {
            arr[i+1]=arr[i];
        }
        pointer++;
        arr[index]= value;
        return value;
    }
    /** delete from index position*/
    public T remove(int index){
        checkIndex(index, "on 'remove'");
        T resault= arr[index];
        for (int i = index; i < pointer ; i++) {
            arr[i]=arr[i+1];
        }
        pointer--;
        return resault;
    }
    /** get elem by index*/
    public T get(int index){
        checkIndex(index, "on 'get'");
        return arr[index];
    }
    /** get count of elems*/
    public int length(){
        return pointer+1;
    }
    /** get size of arr*/
    public int capacity(){
        return arr.length;
    }
    /** Iterator */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cur=0;
            @Override
            public boolean hasNext() {
                return cur<=pointer;
            }
            @Override
            public T next() {
                return arr[cur++];
            }
        };
    }

    /** up size of array */
    private void upSizeArray(){
        T[] newarr= (T[]) new Object[arr.length*2];
        for(int i=0;i<arr.length;i++)
            newarr[i]= arr[i];
        arr= newarr;
    }
    /** help methods: IndexOutOfBoundsException(message) */
    private void checkIndex(int index, String message){
        if(index<0 || index>pointer) throw new IndexOutOfBoundsException(message);
    }
    /** toString*/
    @Override
    public String toString(){
        if(pointer==-1) return "[ ]";
        else return Arrays.toString(Arrays.copyOfRange(arr,0,pointer+1));
    }
}

