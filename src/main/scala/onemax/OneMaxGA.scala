package onemax

import scala.util.Random

class OneMaxGA(mu: Int, lambda: Int, rand: Random, size: Int) extends
ga.GA[OneMaxIndividual](rand, List(size), mu, lambda, new OneMaxGen(size, rand), new ga.TournamentSelection[OneMaxIndividual](2, rand), new OneMaxCross(rand), new OneMaxFit()) {
  
  def mate(p1: OneMaxIndividual, p2: OneMaxIndividual): OneMaxIndividual = {
    return p1
  }
  
  def mutate(p1: OneMaxIndividual): OneMaxIndividual = {
    val inx = rand.nextInt(p1.rep.length)
    val rep = p1.rep.updated(inx, !p1.rep(inx))
    return new OneMaxIndividual(rep)
  }
  
  def select(): OneMaxIndividual = {
    return population.head
  }
}