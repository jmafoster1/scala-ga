package gp.tree

import scala.util.Random
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import Exp._

object OneMaxRunner {
  def main(args: Array[String]): Unit = {
    val rand = new Random(300)
    val intVars = List(Exp.I(0), Exp.R(1))
    val intVals = List(1l, 2l, 3l)
    val stringVars = List()
    val stringVals = List()

    val guardTrain = new HashSetValuedHashMap[List[(Exp.vname, Object)], Boolean]()
    val guardGA = new BoolExpGA(guardTrain, intVars, intVals, stringVars, stringVals, 100, 1, rand, 3)
    println("Best guard: " + guardGA.evolve(100))

    val arithTrain = new HashSetValuedHashMap[List[(Exp.vname, Object)], Long]()
    val arithGA = new IntExpGA(arithTrain, intVars, intVals, 3, 1, rand, 3)
    println("Best arith: " + arithGA.evolve(100))
    
    val cross = new IntCrossover(rand)
    
    val tree1 = Plus(L(1), Minus(V(I(1)), L(2)))
    
    val tree2 = Minus(Times(V(I(1)), L(2)), V(R(2)))
    
    println("tree1: "+tree1)
    println("tree2: "+tree2)
    
    val (n1, p1) = cross.crossoverPoint(tree1)
    val (n2, p2) = cross.crossoverPoint(tree2)

    println(n1 + " -> " + p1(n2))
    println(n2 + " -> " + p2(n1))
    
    println(p1(n1))
    println(p2(n2))

  }
}