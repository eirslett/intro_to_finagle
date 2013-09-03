package javaexample.chaining;

import com.twitter.util.Future;

public class FutureDummy {
    public static Future<Integer> doA(){
        return Future.value(1);
    }
    public static Future<Integer> doB(Integer a){
        return Future.value(a+1);
    }
    public static Future<Integer> doC(Integer b){
        return Future.value(b+1);
    }
    public static Future<Integer> doD(Integer c){
        return Future.value(c+1);
    }
}
