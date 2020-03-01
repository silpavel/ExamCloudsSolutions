package ru.mail.nn.pasha.first;
import ru.mail.nn.pasha.collections.ArrayListT;
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
