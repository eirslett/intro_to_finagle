package javaexample;

import java.util.List;

import com.twitter.finagle.Service;
import com.twitter.finagle.Thrift;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.util.Future;
import com.twitter.util.FutureTransformer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;

import static javaexample.SimpleHelper.implode;

public final class RecipeWebServerHandler extends Service<HttpRequest, HttpResponse> {

    private final RecipeService.ServiceIface client;

    public RecipeWebServerHandler(){
        Service<ThriftClientRequest, byte[]> service = Thrift.newClient("inet!localhost:1234").toService();
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
        client = new RecipeService.ServiceToClient(
                service,
                protocolFactory
        );
    }

    @Override
    public Future<HttpResponse> apply(HttpRequest request) {
        int recipeId = 1;
        return client.getRecipe(recipeId).transformedBy(new FutureTransformer<Recipe, HttpResponse>() {
            public HttpResponse map(Recipe recipe) {
                return SimpleHelper.makeTextResponse(recipeToString(recipe));
            }
        });
    }

    private String recipeToString(Recipe recipe){
        return recipe.getName()+" has ingredients: "+implode(recipe.getIngredients());
    }
}
