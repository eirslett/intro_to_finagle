package javaexample;

import java.net.InetSocketAddress;

import com.twitter.finagle.HttpServer;
import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.util.Await;

public final class RecipeWebServer {
    public static void main(String[] args) throws Exception {
        RecipeWebServerHandler handler = new RecipeWebServerHandler();
        InetSocketAddress listen = new InetSocketAddress(5678);
        ListeningServer server = HttpServer.serve(listen, handler);
        Await.result(server);
    }
}
