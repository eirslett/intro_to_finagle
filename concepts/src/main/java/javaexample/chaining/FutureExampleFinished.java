package javaexample.chaining;

import com.twitter.util.Future;
import com.twitter.util.FutureTransformer;

import static javaexample.chaining.FutureDummy.*;

public class FutureExampleFinished {
    public static void main(String[] args) {
        doA().transformedBy(new FutureTransformer<Integer, Integer>() {
            public Future<Integer> flatMap(Integer value) {
                return doB(value);
            }
        }).transformedBy(new FutureTransformer<Integer, Integer>() {
            public Future<Integer> flatMap(Integer value) {
                return doC(value);
            }
        }).transformedBy(new FutureTransformer<Integer, Integer>() {
            public Future<Integer> flatMap(Integer value) {
                return doD(value);
            }

        }).transformedBy(new FutureTransformer<Integer, Void>() {
            public Void map(Integer value) {
                System.out.println(value);
                return null;
            }
        });
    }
}
