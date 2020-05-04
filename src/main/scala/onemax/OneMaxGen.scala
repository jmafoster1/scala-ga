package onemax

import scala.util.Random

class OneMaxGen(size: Integer, rand: Random) extends ga.Generator[ga.Individual[List[Boolean]]](rand) {
  def generate(): ga.Individual[List[Boolean]] = {
    new ga.Individual[List[Boolean]]((1 to size).map(i => rand.nextBoolean()).toList)
  }
}