package gp.tree

import scala.util.Random

class IntExpGenerator(size: Int, vars: List[Exp.vname], vs: List[Int], rand: Random) extends ga.Generator[ga.Individual[Exp.exp[Int]]](rand) {

  val vals = (vs ++ List(0, 1, 2)).distinct
  
  def randomTerminal(): Exp.exp[Int] = {
    if (rand.nextBoolean() && !vars.isEmpty) {
      Exp.V(rand.shuffle(vars).head)
    }
    else {
      Exp.L(rand.shuffle(vals).head)
    }
  }

  def generateExpression(s: Int): Exp.exp[Int] = {
    if (rand.nextDouble() > 0.8 || s < 2) {
      return randomTerminal()
    } else {
      rand.nextInt(3) match {
        case 0 => return Exp.Plus(generateExpression(s - 1), generateExpression(s - 1))
        case 1 => return Exp.Minus(generateExpression(s - 1), generateExpression(s - 1))
        case 2 => return Exp.Times(generateExpression(s - 1), generateExpression(s - 1))
      }
    }
  }

  def generate(): ga.Individual[Exp.exp[Int]] = {
    return new ga.Individual(generateExpression(size))
  }
}

class StrExpGenerator(size: Int, vars: List[Exp.vname], vs: List[String], rand: Random) extends ga.Generator[ga.Individual[Exp.exp[String]]](rand) {
  
  val vals = (vs ++ List("")).distinct
  
  def generateExpression(): Exp.exp[String] = {
    if (rand.nextBoolean() && !vars.isEmpty) {
      Exp.V(rand.shuffle(vars).head)
    }
    else {
      Exp.L(rand.shuffle(vals).head)
    }
  }

  def generate(): ga.Individual[Exp.exp[String]] = {
    return new ga.Individual(generateExpression())
  }
}

class BoolExpGenerator(size: Int, intVars: List[Exp.vname], intVals: List[Int], strVars: List[Exp.vname], strVals: List[String], rand: Random) extends ga.Generator[ga.Individual[Exp.exp[Boolean]]](rand) {

  val intGen = new IntExpGenerator(size, intVars, intVals, rand)
  val strGen = new StrExpGenerator(size, strVars, strVals, rand)
  
  def generateExpression(s: Int): Exp.exp[Boolean] = {
    if (rand.nextDouble() > 0.8 || s < 2) {
      return Exp.Bc(rand.nextBoolean())
    } else {
      rand.nextInt(6) match {
        case 0 => return Exp.And(generateExpression(s - 1), generateExpression(s - 1))
        case 1 => return Exp.Or(generateExpression(s - 1), generateExpression(s - 1))
        case 2 => return Exp.Not(generateExpression(s - 1))
        case 3 => return Exp.Eq(intGen.generateExpression(s - 1), intGen.generateExpression(s - 1))
        case 4 => return Exp.Gt(intGen.generateExpression(s - 1), intGen.generateExpression(s - 1))
        case 5 => return Exp.Eq(strGen.generateExpression(), strGen.generateExpression())
      }
    }
  }

  def generate(): ga.Individual[Exp.exp[Boolean]] = {
    return new ga.Individual(generateExpression(size))
  }
}