package javaexample;

import java.net.InetSocketAddress;

import com.twitter.finagle.Http;
import com.twitter.finagle.ListeningServer;
import com.twitter.util.Await;

public final class RecipeWebServerFinished {
    public static void main(String[] args) throws Exception {
        RecipeWebServerImplFinished service = new RecipeWebServerImplFinished();

        InetSocketAddress listen = new InetSocketAddress(5678);
        ListeningServer server = Http.serve(listen, service);
        Await.result(server);
    }
}
