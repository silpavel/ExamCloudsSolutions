package ru.mail.nn.pasha.first;
import ru.mail.nn.pasha.second.ArrayListT;
import ru.mail.nn.pasha.second.LinkedListT;
import ru.mail.nn.pasha.second.StackT;

import java.util.function.Consumer;

/** main class */
public class Main {
    /** Enter point
     * @param args string of initialization
     */
    public static void main(String[] args) {
        ArrayListT<Payload> pl= new ArrayListT<>(4);
        pl.add(new Payload("R1",1));
        pl.add(new Payload("R2",2));
        pl.add(new Payload("R3",3));
        pl.add(new Payload("R4",4));
        pl.add(new Payload("R5",5));
        for (Payload pay: pl) {
            System.out.println(pay);
        }
    }
}
// next class for testing LinkedListT<T>
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
