package javaexample.chaining;

public class AsyncCallbackDummy {
    // Helper interface needed to implement callback structures
    public static interface Function<S,T>{
        T apply(S value);
    }

    // Assume doA, doB, doC and doD are long-running, taking 2-3 seconds each.
    // For example by executing an advanced query.
    public static <T> void doA(final Function<Integer,T> callback){
        new Thread(new Runnable() {
            public void run() {
                int initialValue = 1;
                callback.apply(initialValue);
            }
        }).run();
    }

    public static <T> void doB(final Integer input, final Function<Integer,T> callback){
        new Thread(new Runnable(){
            public void run() {
                int computedResult = input + 1;
                callback.apply(computedResult);
            }
        }).run();
    }

    public static <T> void doC(final Integer input, final Function<Integer,T> callback){
        new Thread(new Runnable(){
            public void run() {
                int computedResult = input + 1;
                callback.apply(computedResult);
            }
        }).run();
    }

    public static <T> void doD(final Integer input, final Function<Integer,T> callback){
        new Thread(new Runnable(){
            public void run() {
                int computedResult = input + 1;
                callback.apply(computedResult);
            }
        }).run();
    }
}
