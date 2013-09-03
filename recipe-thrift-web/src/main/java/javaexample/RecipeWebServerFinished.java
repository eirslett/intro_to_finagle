package javaexample;

import com.twitter.finagle.Http;
import com.twitter.finagle.ListeningServer;
import com.twitter.util.Await;
import com.twitter.finagle.Service;
import com.twitter.util.Future;
import com.twitter.util.FutureTransformer;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;

import java.net.InetSocketAddress;

import static javaexample.SimpleHelper.*;

public final class RecipeWebServerFinished {
    public static void main(String[] args) throws Exception {
        Service<HttpRequest,HttpResponse> handler = getHandler();
        InetSocketAddress listen = new InetSocketAddress(8080);
        ListeningServer server = Http.serve(listen, handler);
        Await.result(server);
    }

    private static Service<HttpRequest, HttpResponse> getHandler() {
        final RecipeService.ServiceIface client = createRecipeThriftClient();
        return new Service<HttpRequest, HttpResponse>() {
            public Future<HttpResponse> apply(HttpRequest request) {
                return client.getRecipe(1).transformedBy(new FutureTransformer<Recipe, HttpResponse>() {
                    public HttpResponse map(Recipe recipe) {
                        return makeTextResponse(recipeToString(recipe));
                    }
                });
            }
        };
    }
}
