package javaexample;

import java.net.InetSocketAddress;

import com.twitter.finagle.builder.ServerBuilder;

public final class RecipeWebServer {
    public static void main(String[] args) {
        RecipeWebServerHandler handler = new RecipeWebServerHandler();
        InetSocketAddress listen = new InetSocketAddress(5678);

        /*
        ListeningServer server = HttpServer.serve(listen, handler);
        while(true){

        }
        */

        ServerBuilder.safeBuild(handler, ServerBuilder.get()
                .codec(com.twitter.finagle.http.Http.get())
                .name("HttpServer")
                .bindTo(listen));


    }
}
