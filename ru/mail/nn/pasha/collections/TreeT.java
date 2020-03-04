package ru.mail.nn.pasha.collections;
import java.util.Iterator;
/** Binary tree*/
public class TreeT<T extends Comparable<T>> implements Iterable<T>{
    private Node<T> root;//Node where up == null
    // ---
    public T add(T value){
        if(root == null) root= new Node(value, null);
        else root.add(value);
        return value;
    }
    @Override
    public String toString(){
        return (root == null) ? "[ ]" : '[' + root.toString() + ']';
    }
    @Override
    public Iterator<T> iterator(){
        return new Iterator(){
            @Override
            public boolean hasNext(){

                return false;
            }
            @Override
            public T next(){
                return null;
            }
        };
    }
    // ---
    private class Node<T extends Comparable<T>> implements Iterable<T>{
        Node up, left, right;
        T payload;
        Node(T value, Node up){
            payload= value;
            this.up= up;
            left= right= null;
        }
        T add(T value){
            // next use compareTo or compare
            int resault= payload.compareTo(value);
            if(resault>0){//use left
                if(left != null)
                    left.add(value);
                else
                    left= new Node(value, this);
            }else if(resault<0){//use right
                if(right != null)
                    right.add(value);
                else
                    right= new Node(value, this);
            }
            return value;
        }
        @Override
        public String toString(){
            StringBuilder resault= new StringBuilder("");
            if(left != null) resault.append(left);
            resault.append(payload);
            resault.append(" ");
            if(right != null) resault.append(right);
            return resault.toString();
        }
        public Iterator<T> iterator(){
            return new Iterator(){
                Iterator<T> it;
                char direct='L';// _L_eft, _P_ayload, _R_ight
                @Override
                public boolean hasNext(){
                    return false;
                }
                @Override
                public T next(){
                    return null;
                }
            };
        }
    }
}
