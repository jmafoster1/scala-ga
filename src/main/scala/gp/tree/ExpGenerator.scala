package gp.tree

import scala.util.Random

class IntExpGenerator(size: Integer, rand: Random) extends ga.Generator[ga.Individual[Exp.exp[Int]]](rand) {
  def generate(): ga.Individual[Exp.exp[Int]] = {
    return new ga.Individual(Exp.L(0))
  }
}