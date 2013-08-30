package javaexample;

import java.net.InetSocketAddress;

import com.twitter.finagle.Thrift;
import org.apache.thrift.protocol.TBinaryProtocol;
import javaexample.RecipeService.Service;

public final class RecipeThriftServer {
    public static void main(String[] args) {
        RecipeThriftServerHandler handler = new RecipeThriftServerHandler();
        Service service = new Service(handler, new TBinaryProtocol.Factory());
        InetSocketAddress listen = new InetSocketAddress(1234);
        Thrift.serve(listen, service);
        while(true){

        }
    }
}
