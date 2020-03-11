package ru.mail.nn.pasha.iostreamtasks;

import java.io.Serializable;
import java.util.Arrays;

public class Person implements Serializable{

    private String name;
    private byte age;
    private long count;
    private int[] upDownCountHistory;
    public Person(String name, byte age, long count, int[] upDownCountHistory) {
        this.name = name;
        this.age = age;
        this.count = count;
        this.upDownCountHistory = upDownCountHistory;
    }
    public String getName() {
        return name;
    }
    public byte getAge() {
        return age;
    }
    public long getCount() {
        return count;
    }
    public int[] getUpDownCountHistory() {
        return upDownCountHistory;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", count=" + count +
                ", upDownCountHistory=" + Arrays.toString(upDownCountHistory) +
                '}';
    }

}
