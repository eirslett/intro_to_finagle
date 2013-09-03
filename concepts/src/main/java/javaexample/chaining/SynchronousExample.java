package javaexample.chaining;

import static javaexample.chaining.SynchronousDummy.*;

public class SynchronousExample {
    public static void main(String[] args) {
        int a = doA(); // blocks the thread while executing
        int b = doB(a); // blocks the thread while executing
        int c = doC(b); // blocks the thread while executing
        int d = doD(c); // blocks the thread while executing
        System.out.println(d);
    }
}
