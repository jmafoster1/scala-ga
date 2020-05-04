package gp.tree

import scala.util.Random
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

object OneMaxRunner {
  def main(args: Array[String]): Unit = {
    val rand = new Random(300)
    val intVars = List(Exp.I(0), Exp.R(1))
    val intVals = List(1, 2, 3)
    val stringVars = List()
    val stringVals = List()

    val guardTrain = new HashSetValuedHashMap[List[(Exp.vname, Object)], Boolean]()
    val guardGA = new BoolExpGA(guardTrain, intVars, intVals, stringVars, stringVals, 100, 1, rand, 3)
    println("Best guard: " + guardGA.evolve(100))

    val arithTrain = new HashSetValuedHashMap[List[(Exp.vname, Object)], Int]()
    val arithGA = new IntExpGA(arithTrain, intVars, intVals, 3, 1, rand, 3)
    println("Best arith: " + arithGA.evolve(100))
  }
}