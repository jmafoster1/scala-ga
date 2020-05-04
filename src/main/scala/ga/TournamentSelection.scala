package ga

import scala.util.Random

class TournamentSelection[A <% Ordered[A]](n:Int, rand: Random) extends SelectionFunction[A] {
  def select(population: List[A]): (A, A) = {
    val pool1 = rand.shuffle(population).take(n)
    val pool2 = rand.shuffle(population).take(n)
    return (pool1.max, pool2.max)
  }
}