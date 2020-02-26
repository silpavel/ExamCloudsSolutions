package ru.mail.nn.pasha.first;

import java.util.function.Consumer;

/** main class */
public class Main {
    /** Enter point
     * @param args string of initialization
     */
    public static void main(String[] args) {
        MatrixCalculator mx1= new MatrixCalculator(2,2);
        MatrixCalculator mx2= new MatrixCalculator(2,2);
        MatrixCalculator mxResault;
        mx1.fill();
        mx2.fill();
        mxResault= mx1.add(mx2);
        System.out.println(mx1);
        System.out.println(mx2);
        System.out.println(mxResault);
        System.out.println(mxResault.muxN(2));
    }
}
