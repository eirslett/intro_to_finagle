package scalaexample.chaining

import FutureDummy._

object FutureExampleFinished extends App {
  doA() flatMap doB flatMap doC flatMap doD map {result =>
    println(result)
  }
}
