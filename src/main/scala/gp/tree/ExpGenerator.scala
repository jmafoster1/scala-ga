package gp.tree

import scala.util.Random

class IntExpGenerator(size: Integer, rand: Random) extends ga.Generator[ExpIndividual[Int]](rand) {
  def generate(): ExpIndividual[Int] = {
    return new ExpIndividual(Exp.L(0))
  }
}