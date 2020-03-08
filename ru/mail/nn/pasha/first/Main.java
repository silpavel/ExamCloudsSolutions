package ru.mail.nn.pasha.first;
import ru.mail.nn.pasha.collectiontasks.*;

import java.util.Date;
import java.util.Random;

/** main class */
public class Main {
    /** Enter point
     * @param args string of initialization
     */
    public static void main(String[] args) {
        new Tasks().task11();
    }
}
// next class for testing <T>
class Payload implements Comparable<Payload>{
    private String name;
    private int age;
    // constructors
    public Payload(String name, int age) {
        this.name = name;
        this.age = age;
    }
    // get/set
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    //methods
    @Override
    public String toString() {
        return '{' + name + ", " +age + '}';
    }
    @Override
    public int compareTo(Payload pl){
        int resault= name.compareTo(pl.name);
        if(resault == 0) resault= age-pl.age;
        return resault;
    }
}
class A{
    static void fA(){}
    class B{

    }
}
