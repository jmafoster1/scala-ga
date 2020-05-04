package ga

abstract class CrossoverFunction[A](rand: scala.util.Random) {
  def mate(p1: A, p2: A): A
  def mutate(p1: A): A

}