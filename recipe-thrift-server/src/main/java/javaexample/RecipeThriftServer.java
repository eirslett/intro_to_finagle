package javaexample;

import java.net.InetSocketAddress;

import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Thrift;
import com.twitter.util.Await;
import org.apache.thrift.protocol.TBinaryProtocol;
import javaexample.RecipeService.Service;

public final class RecipeThriftServer {
    public static void main(String[] args) throws Exception {
        RecipeThriftServerHandler handler = new RecipeThriftServerHandler();
        RecipeService.Service service = new RecipeService.Service(handler, new TBinaryProtocol.Factory());
        InetSocketAddress listen = new InetSocketAddress(1234);
        ListeningServer server = Thrift.serve(listen, service);
        Await.result(server);
    }
}
