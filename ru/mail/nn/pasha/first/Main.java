package ru.mail.nn.pasha.first;
import ru.mail.nn.pasha.collections.ArrayListT;
import ru.mail.nn.pasha.collections.QueueArray;

import java.util.Date;
import java.util.Random;

/** main class */
public class Main {
    /** Enter point
     * @param args string of initialization
     */
    public static void main(String[] args) {
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




    }
}
// next class for testing <T>
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
