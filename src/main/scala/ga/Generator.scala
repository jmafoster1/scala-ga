package ga

import scala.util.Random

abstract class Generator[A](rand: Random) {
  def generate(): A
}