package javaexample;

import java.net.InetSocketAddress;

import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Thrift;
import com.twitter.util.Await;
import org.apache.thrift.protocol.TBinaryProtocol;

public final class RecipeThriftServerFinished {
    public static void main(String[] args) throws Exception {
        RecipeThriftServiceImplFinished handler = new RecipeThriftServiceImplFinished();
        RecipeService.Service service = new RecipeService.Service(handler, new TBinaryProtocol.Factory());

        InetSocketAddress listen = new InetSocketAddress(1234);
        ListeningServer server = Thrift.serve(listen, service);
        Await.result(server);
    }
}
