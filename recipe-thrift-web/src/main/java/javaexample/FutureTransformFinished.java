package javaexample;

import com.twitter.util.Await;
import com.twitter.util.Future;
import com.twitter.util.FutureTransformer;

public class FutureTransformFinished {
    public static void main(String[] args) throws Exception {
        Future<String> aFuture = Future.value("a");
        Future<String> abcFuture =
        aFuture.transformedBy(new FutureTransformer<String, String>() {
            @Override
            public String map(String value) {
                return value+"b";
            }
        }).transformedBy(new FutureTransformer<String, String>() {
            @Override
            public String map(String value) {
                return value+"c";
            }
        });
        System.out.println(Await.result(abcFuture));
    }
}
