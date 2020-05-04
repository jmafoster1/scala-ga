package gp.tree

import scala.util.Random
import org.apache.commons.collections4.MultiValuedMap

class IntExpGA(evalSet: MultiValuedMap[List[(Exp.vname, Object)],Int], vars: List[Exp.vname], vals: List[Int], lambda: Int, mu: Int, rand: Random, size: Int) extends ga.GA[ga.Individual[Exp.exp[Int]]](
  rand,
  0,
  lambda,
  mu,
  new IntExpGenerator(size, vars, vals, rand),
  new ga.TournamentSelection[ga.Individual[Exp.exp[Int]]](2, rand),
  new ExpCrossover(rand),
  new IntExpFitness(evalSet, vals)) {}

class BoolExpGA(evalSet: MultiValuedMap[List[(Exp.vname, Object)],Boolean], intVars: List[Exp.vname], intVals: List[Int], strVars: List[Exp.vname], strVals: List[String], lambda: Int, mu: Int, rand: Random, size: Int) extends ga.GA[ga.Individual[Exp.exp[Boolean]]](
  rand,
  0,
  lambda,
  mu,
  new BoolExpGenerator(size, intVars, intVals, strVars, strVals, rand),
  new ga.TournamentSelection[ga.Individual[Exp.exp[Boolean]]](2, rand),
  new ExpCrossover(rand),
  new BoolExpFitness(evalSet)) {}