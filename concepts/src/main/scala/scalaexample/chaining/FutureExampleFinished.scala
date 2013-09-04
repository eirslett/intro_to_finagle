package scalaexample.chaining

import FutureDummy._

object FutureExampleFinished extends App {
  doA() flatMap doB flatMap doC flatMap doD map {result =>
    println(result)
  }

  // or, with for comprehensions:

  for {
    a <- doA()
    b <- doB(a)
    c <- doC(b)
    d <- doD( c )
  } yield println(d)
}
