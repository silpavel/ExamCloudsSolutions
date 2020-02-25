package ru.mail.nn.pasha.first;

import java.util.function.Consumer;

/** main class */
public class Main {
    /** Enter point
     * @param args string of initialization
     */
    public static void main(String[] args) {
        //Algorithms.testSort(5, Algorithms::bubbleSort);
        Algorithms.testSort(0, Algorithms::selectSort);
    }
}
