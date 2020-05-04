package onemax

import scala.util.Random

class OneMaxGen(size: Integer, rand: Random) extends ga.Generator[OneMaxIndividual](rand) {
  def generate(): OneMaxIndividual = {
    new OneMaxIndividual((1 to size).map(i => rand.nextBoolean()).toList)
  }
}