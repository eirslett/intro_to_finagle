package javaexample;

import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Service;
import com.twitter.finagle.Thrift;
import com.twitter.util.Await;
import com.twitter.util.Future;

import java.net.InetSocketAddress;

import static javaexample.SimpleThriftHelper.*;

public final class RecipeThriftServerFinished {
    public static void main(String[] args) throws Exception {
        RecipeService.ServiceIface serviceImpl = getServiceImpl();
        InetSocketAddress listen = new InetSocketAddress(1234);

        Service<byte[],byte[]> handler = toService(serviceImpl);
        ListeningServer server = Thrift.serve(listen,handler);

        Await.result(server);
    }

    private static RecipeService.ServiceIface getServiceImpl() {
        return new RecipeService.ServiceIface() {
            public Future<Recipe> getRecipe(int recipeId) {
//                return Future.value(simpleCakeRecipe());
                return MockDatabase.getRecipeById(recipeId);
            }
        };
    }
}
