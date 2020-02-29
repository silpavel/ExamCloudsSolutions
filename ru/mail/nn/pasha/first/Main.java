package ru.mail.nn.pasha.first;
import ru.mail.nn.pasha.second.LinkedListT;

import java.util.function.Consumer;

/** main class */
public class Main {
    /** Enter point
     * @param args string of initialization
     */
    public static void main(String[] args) {

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
