package javaexample.chaining;

public class SynchronousDummy {

    // Assume doA, doB, doC and doD are long-running, taking 2-3 seconds each.
    // For example by executing an advanced query.
    public static int doA(){
        int initialValue = 1;
        return initialValue;
    }
    public static int doB(int a){
        int computedResult = a + 1;
        return computedResult;
    }
    public static int doC(int b){
        int computedResult = b + 1;
        return computedResult;
    }
    public static int doD(int c){
        int computedResult = c + 1;
        return computedResult;
    }
}
