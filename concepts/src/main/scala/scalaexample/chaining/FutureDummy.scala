package scalaexample.chaining

import com.twitter.util.Future

object FutureDummy {
  def doA() = Future(1)
  def doB(a: Int) = Future(a+1)
  def doC(b: Int) = Future(b+1)
  def doD(c: Int) = Future(c+1)
}
