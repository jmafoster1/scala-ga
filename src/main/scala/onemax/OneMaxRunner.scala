package onemax

import scala.util.Random

object OneMaxRunner {
    def main(args: Array[String]): Unit = {
      println("Hello World")
      val rand = new Random(1)
      val ga = new OneMaxGA(1, 1, rand, 5)
      val best = ga.evolve(100)
      println("Best: "+best)
    }
}