package javaexample;

import com.twitter.finagle.Service;
import com.twitter.util.Future;
import com.twitter.util.FutureTransformer;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;

import static javaexample.SimpleHelper.createRecipeThriftClient;
import static javaexample.SimpleHelper.makeTextResponse;
import static javaexample.SimpleHelper.recipeToString;

public final class RecipeWebServerImplFinished extends Service<HttpRequest, HttpResponse> {

    private final RecipeService.ServiceIface client;

    public RecipeWebServerImplFinished(){
        client = createRecipeThriftClient();
    }

    @Override
    public Future<HttpResponse> apply(HttpRequest request) {
        int recipeId = 1;
        return client.getRecipe(recipeId).transformedBy(new FutureTransformer<Recipe, HttpResponse>() {
            public HttpResponse map(Recipe recipe) {
                return makeTextResponse(recipeToString(recipe));
            }
        });
    }
}
