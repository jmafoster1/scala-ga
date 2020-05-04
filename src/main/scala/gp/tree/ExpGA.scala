package gp.tree

import scala.util.Random

class IntExpGA(rand: Random, lambda: Int, mu: Int, size: Int) extends 
ga.GA[ga.Individual[Exp.exp[Int]]](
    rand,
    List(0),
    lambda,
    mu,
    new IntExpGenerator(size, rand),
    new ga.TournamentSelection[ga.Individual[Exp.exp[Int]]](2, rand),
    new ExpCrossover(rand),
    new IntExpFitness()) {

}