package ga

import scala.util.Random

abstract class GA[A <% Individual[_] with Ordered[A]](
    rand: Random,
    optimal: List[Double],
    lambda: Integer,
    mu: Integer,
    gen: Generator[A],
    sel: SelectionFunction[A],
    cross: CrossoverFunction[A],
    fit: FitnessFunction[A]) {

  var population = List[A]()

  def crossover(): A = {
    val (p1, p2) = sel.select(population)

    return cross.mate(p1, p2)
  }

  def iterate(numGens: Integer): A = {
    for (i <- 0 to numGens) {
      population.find(i => i.compare_lists(i.fitness, optimal) >= 0) match {
        case Some(i) => return i
        case None => {}
      }

      val offspring = (0 to lambda - 1).map(x => crossover()).map(x => cross.mutate(x))
      for (i <- offspring)
        i.fitness = fit.evaluate(i)
      println(population ++ offspring)
      population = (population ++ offspring).sorted.reverse.take(mu)
    }
    return population.head
  }

  def evolve(numGens: Integer): A = {
    population = (0 to mu - 1).map(x => gen.generate()).toList
    for (i <- population)
      i.fitness = fit.evaluate(i)
    println("Initial pop: " + population)
    return iterate(numGens)
  }
}