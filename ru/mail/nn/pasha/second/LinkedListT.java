package ru.mail.nn.pasha.second;

import java.util.Iterator;

public class LinkedListT<T> implements Iterable<T>{
    private Node head;
    private Node last;
    private int length;
    //constructors
    public LinkedListT(){
        head=last=null;
        length=0;
    }
    // getters\setters
    public int getLength(){return length;}
    // methods
    /** add value to end */
    public T addLast(T value){
        if(head == null){// length==0
            head = last = new Node(value, null, null);
        }else if(head == last){// length==1
            last= head.next= new Node(value, head, null);
        }else{// length > 1
            last= last.next= new Node(value, last, null);
        }
        return value;
    }
    /** add to begin */
    public T addHead(T value){
        head= new Node(value, null, head);
        if(head.next != null) head.next.prev= head;
        else last=head;
        return value;
    }
    /** add to index position */
    public T add(T value, int index){
        if(index <= 0 || index>= length) new IndexOutOfBoundsException("add by index");
        if(index == 0){
            addHead(value);
        }else if(index==length-1){
            addLast(value);
        }else{
            Node prev= getNode(index).prev;
            Node next= prev.next; // getNode(index)
            prev.next= new Node(value, prev, next);
            next.prev= prev.next;// new Node
        }
        return value;
    }
    /** replace value by index*/
    public T replace(T value, int index){
        getNode(index).payload= value;
        return value;
    }
    /** remove element from list by index*/
    public T remove(int index){
        Node forDel= getNode(index);
        if(forDel == head){// and last==head
            head= head.next;
        }else if(forDel == last){// forDel != head -> last != head
            last= forDel.prev;
            last.next= null;
        }else{
            Node prev= forDel.prev;
            Node next= forDel.next;
            prev.next= next;
            next.prev= prev;
        }
        forDel.next= forDel.prev= null;
        length--;
        return forDel.payload;
    }
    /** get T value*/
    public T get(int index){
        return getNode(index).payload;
    }
    /** reverse list*/
    public void reverse(){
        if(head == null || head == last) return;
        LinkedListT<T> newlli= new LinkedListT();
        Node prevHead= null;
        while(head != null){
            newlli.head= head;
            newlli.head.prev= head.next;
            head= head.next;
            newlli.head.next= prevHead;
            prevHead= newlli.head;
        }
        head= newlli.head;
    }
    /** for each*/
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node cur = head;
            @Override
            public boolean hasNext() {
                return cur != null;
            }
            @Override
            public T next() {
                T value = cur.payload;
                cur = cur.next;
                return value;
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    /** empty list? */
    public boolean isEmpty(){ return head == null;}
    /** get object Node with certain index*/
    private Node getNode(int index){
        if(index<0 || index>=length) throw new IndexOutOfBoundsException("getNode");
        if(index == 0) return head;
        if(index == length-1) return last;
        Node current;
        if(index <= (length/2 - 1) ){
            current= head;
            for(int i=1; i<=index; i++){
                current=current.next;
            }
        }
        else{
            current= last;
            for(int i=length-2; i>=index; i--){
                current=current.prev;
            }
        }
        return current;
    }
    /** toString*/
    @Override
    public String toString(){
        if(head == null) return "[ ]";
        StringBuilder s= new StringBuilder("[ ");
        Node current= head;
        while(current.next != null){
            s.append(current.toString()).append(", ");
            current= current.next;
        }
        s.append(current.toString()).append(" ]");
        return s.toString();
    }
    //classes
    private class Node{
        Node prev;
        Node next;
        T payload;
        //
        Node(T payload, Node prev, Node next){
            this.payload= payload;
            this.prev= prev;
            this.next= next;
            length++;
        }
        //
        @Override
        public String toString(){
            return "" + payload.toString();
        }
    }
}
/*  next class for testing LinkedListT<T>, place it in Main or make it public
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

