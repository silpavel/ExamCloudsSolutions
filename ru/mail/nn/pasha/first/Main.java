package ru.mail.nn.pasha.first;
import ru.mail.nn.pasha.collections.ArrayListT;
import ru.mail.nn.pasha.collections.PriorityQueueT;
import ru.mail.nn.pasha.collections.QueueArray;
import ru.mail.nn.pasha.collections.QueueTwoDirect;

import java.util.Date;
import java.util.Random;

/** main class */
public class Main {
    /** Enter point
     * @param args string of initialization
     */
    public static void main(String[] args) {
        PriorityQueueT<Payload> payloads= new PriorityQueueT<>(5);
        payloads.add(new Payload("Pa", 15), 2);
        payloads.add(new Payload("Paul", 15), 4);
        //payloads.poll(4);
        System.out.println(payloads.isEmpty(4));


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
