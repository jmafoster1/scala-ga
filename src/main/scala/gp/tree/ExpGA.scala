package gp.tree

import scala.util.Random
import org.apache.commons.collections4.MultiValuedMap

class IntExpGA(evalSet: MultiValuedMap[List[(Exp.vname, Object)],Long], vars: List[Exp.vname], vals: List[Long], lambda: Int, mu: Int, rand: Random, size: Int) extends ga.GA[ga.Individual[Exp.exp[Long]]](
  rand,
  0,
  lambda,
  mu,
  new IntExpGenerator(size, vars, vals, rand),
  new ga.TournamentSelection[ga.Individual[Exp.exp[Long]]](2, rand),
  new IntCrossover(rand),
  new IntExpFitness(evalSet, vals)) {}

class BoolExpGA(evalSet: MultiValuedMap[List[(Exp.vname, Object)],Boolean], intVars: List[Exp.vname], intVals: List[Long], strVars: List[Exp.vname], strVals: List[String], lambda: Int, mu: Int, rand: Random, size: Int) extends ga.GA[ga.Individual[Exp.exp[Boolean]]](
  rand,
  0,
  lambda,
  mu,
  new BoolExpGenerator(size, intVars, intVals, strVars, strVals, rand),
  new ga.TournamentSelection[ga.Individual[Exp.exp[Boolean]]](2, rand),
  new BoolCrossover(rand),
  new BoolExpFitness(evalSet)) {}