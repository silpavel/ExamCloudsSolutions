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
                return (root != null) && root.iterator().hasNext();
            }
            @Override
            public T next(){
                return root.iterator().next();
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
                Iterator<T> li= (left != null) ? left.iterator(): null;
                Iterator<T> ri= (right != null) ? right.iterator(): null;
                char direct='L';// _L_eft, _P_ayload, _R_ight
                @Override
                public boolean hasNext(){
                    if(direct == 'L'){// left
                        if( li != null && li.hasNext())
                            return true;
                        else
                            direct= 'P';
                    }
                    if(direct == 'P') {// payload
                        return true;
                    }
                    if(direct == 'R'){// right
                        if(ri != null && ri.hasNext())
                            return true;
                    }
                    //ri is null or ri.hasNext() is false
                    return false;
                }
                @Override
                public T next(){
                    if(direct == 'L'){
                        return li.next();
                    }else if(direct == 'P'){
                        direct= 'R';
                        return payload;
                    }else{// id direct=='R'
                        return ri.next();
                    }
                }
            };
        }
    }
}
