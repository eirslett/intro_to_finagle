package scalaexample

import com.twitter.util.{Await, Future}

object FutureTransformFinished extends App {
  val aFuture = Future("a")
  val abcFuture = aFuture map {_+"b"} map {_+"c"}
  println(Await result abcFuture)
}
