package onemax

import scala.util.Random

class OneMaxGA(mu: Int, lambda: Int, rand: Random, size: Int) extends
ga.GA[ga.Individual[List[Boolean]]](rand, List(size), mu, lambda, new OneMaxGen(size, rand), new ga.TournamentSelection[ga.Individual[List[Boolean]]](2, rand), new OneMaxCross(rand), new OneMaxFit()) {
  
  def mate(p1: ga.Individual[List[Boolean]], p2: ga.Individual[List[Boolean]]): ga.Individual[List[Boolean]] = {
    return p1
  }
  
  def mutate(p1: ga.Individual[List[Boolean]]): ga.Individual[List[Boolean]] = {
    val inx = rand.nextInt(p1.rep.length)
    val rep = p1.rep.updated(inx, !p1.rep(inx))
    return new ga.Individual[List[Boolean]](rep)
  }
  
  def select(): ga.Individual[List[Boolean]] = {
    return population.head
  }
}