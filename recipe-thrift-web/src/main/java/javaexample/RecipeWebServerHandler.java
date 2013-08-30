package javaexample;

import java.util.List;

import com.twitter.finagle.Service;
import com.twitter.finagle.Thrift;
import com.twitter.finagle.stats.NullStatsReceiver;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.util.Future;
import com.twitter.util.FutureTransformer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

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
        return client.getRecipe(1).transformedBy(new FutureTransformer<Recipe, HttpResponse>() {
            public HttpResponse map(Recipe recipe) {
                return makeTextResponse(recipeToString(recipe));
            }
        });
    }

    private HttpResponse makeTextResponse(String content) {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.setContent(ChannelBuffers.wrappedBuffer(content.getBytes()));
        return response;
    }

    private String recipeToString(Recipe recipe){
        return recipe.getName()+" has ingredients: "+implode(recipe.getIngredients());
    }

    private String implode(List<String> stringList){
        String imploded = "";
        for(String string: stringList){
            imploded += string+" ";
        }
        return imploded;
    }
}
