package scalaexample

import com.twitter.finagle.{Thrift, HttpServer, Service}
import com.twitter.util.{Await, Future}
import org.jboss.netty.handler.codec.http.HttpRequest
import org.jboss.netty.handler.codec.http.HttpResponse
import java.net.InetSocketAddress
import javaexample.{Recipe, SimpleHelper, RecipeService}
import SimpleHelper._
import org.apache.thrift.protocol.TBinaryProtocol

object RecipeWebServer extends App {
  def main(args: List[String]) {

    val service = Thrift.newClient("inet!localhost:1234").toService
    val protocolFactory = new TBinaryProtocol.Factory
    val thriftClient = new RecipeService.ServiceToClient(service,protocolFactory)

    val handler = new Service[HttpRequest,HttpResponse] {
      def apply(request: HttpRequest) = {
        val recipeId = 1
        thriftClient.getRecipe(recipeId) map {
          recipe =>
            makeTextResponse(recipeToString(recipe))
        }
      }
    }
    val listen = new InetSocketAddress(5678)
    val server = HttpServer.serve(listen, handler)

    Await.result(server)
  }

  private def recipeToString(recipe: Recipe) = {
    recipe.getName + " has ingredients "+implode(recipe.getIngredients)
  }
}
