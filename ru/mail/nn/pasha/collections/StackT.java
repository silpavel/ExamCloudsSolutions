package ru.mail.nn.pasha.collections;

public class StackT<T>{
    // fields
    private T[] stack;
    private int pointer;
    // constructors
    public StackT(int size){
        stack= (T[]) new Object[size];
        pointer= -1;
    }
    // methods
    public T push(T value){
        if(isFull()) throw new FullStackTException("push()");
        stack[++pointer]= value;
        return value;
    }
    public T pop(){
        if(pointer==-1) throw new EmptyStackTException("pop()");
        return stack[pointer--];
    }
    public T seek(){
        if(pointer==-1) throw new EmptyStackTException("seek()");
        return stack[pointer];
    }
    public boolean isEmpty(){return pointer == -1;}
    public boolean isFull(){return pointer == stack.length-1;}
}
class FullStackTException extends RuntimeException{
    public FullStackTException(String message) {
        super(message);
    }
}
class EmptyStackTException extends RuntimeException{
    public EmptyStackTException(String message) {
        super(message);
    }
}
