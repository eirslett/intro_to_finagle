package javaexample.chaining;

import static javaexample.chaining.AsyncCallbackDummy.*;

// An example that uses the callback-strategy for asynchronous processing.
// This shouldn't block the thread, but is a mess to read, and difficult to test.
public class AsyncCallbackExample {
    public static void main(String[] args) {
        doA(new Function<Integer, Void>() {
            public Void apply(Integer value) {
                doB(value, new Function<Integer, Void>() {
                    public Void apply(Integer value) {
                        doC(value, new Function<Integer, Void>() {
                            public Void apply(Integer value) {
                                doD(value, new Function<Integer, Void>() {
                                    public Void apply(Integer value) {
                                        System.out.println(value);
                                        return null;
                                    }
                                });
                                return null;
                            }
                        });
                        return null;
                    }
                });
                return null;
            }
        });
    }
}
