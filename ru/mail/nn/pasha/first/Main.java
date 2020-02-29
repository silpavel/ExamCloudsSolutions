package ru.mail.nn.pasha.first;
import ru.mail.nn.pasha.second.LinkedListT;
import ru.mail.nn.pasha.second.StackT;

import java.util.function.Consumer;

/** main class */
public class Main {
    /** Enter point
     * @param args string of initialization
     */
    public static void main(String[] args) {
        StackT<Payload> nt= new StackT<>(3);
        nt.push(new Payload("Paul", 15));
        nt.push(new Payload("Gaul", 25));
        nt.push(new Payload("Faul", 35));
        System.out.println(nt.pop());
        System.out.println(nt.pop());
        System.out.println(nt.pop());
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
