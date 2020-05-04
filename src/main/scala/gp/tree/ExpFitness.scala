package gp.tree

import org.apache.commons.collections4.MultiValuedMap
import scala.collection.JavaConversions._

abstract class ExpFitness[A](evalSet: MultiValuedMap[List[(Exp.vname, Object)],A], latentVals: List[A]) extends ga.FitnessFunction[ga.Individual[Exp.exp[A]]] {
  def distance(expected: A, actual: A): Double

  def totalUsedVars() = Set[Exp.vname]()

  def calculateDistance(current: java.util.Map.Entry[List[(Exp.vname, Object)],A], latent: Set[Exp.vname]): Double = {
    return 0
  }

  def evaluate(individual: ga.Individual[Exp.exp[A]]): List[Double] = {
    var mistakes: Double = 0D
    val distances = List[Double]()
    val latent = individual.rep.latentVars
    val totalVars = totalUsedVars().diff(individual.rep.varsInTree)
    for (current <- evalSet.entries()) {
      val minDistance: Double = calculateDistance(current, latent)
      distances.add(minDistance)
      if (minDistance > 0D) {
        { mistakes += 1; mistakes - 1 }
      }
    }
    val fitness: Double = mistakes + rmsd(distances)
    if (totalVars.isEmpty) fitness
    fitness + latent.size

    List(0, -individual.rep.height)
  }
}

class IntExpFitness(e: MultiValuedMap[List[(Exp.vname, Object)],Int], l: List[Int]) extends ExpFitness[Int](e, l) {
  def distance(expected: Int, actual: Int) = Math.abs(expected - actual)
}

class StrExpFitness(e: MultiValuedMap[List[(Exp.vname, Object)],String], l: List[String]) extends ExpFitness[String](e, l) {

  // From Rosetta Code: https://rosettacode.org/wiki/Levenshtein_distance#Scala
  def distance(s1: String, s2: String): Double = {
    val dist = Array.tabulate(s2.length + 1, s1.length + 1) { (j, i) => if (j == 0) i else if (i == 0) j else 0 }

    @inline
    def minimum(i: Int*): Int = i.min

    for {
      j <- dist.indices.tail
      i <- dist(0).indices.tail
    } dist(j)(i) =
      if (s2(j - 1) == s1(i - 1)) dist(j - 1)(i - 1)
      else minimum(dist(j - 1)(i) + 1, dist(j)(i - 1) + 1, dist(j - 1)(i - 1) + 1)

    dist(s2.length)(s1.length)
  }
}

class BoolExpFitness(e: MultiValuedMap[List[(Exp.vname, Object)],Boolean]) extends ExpFitness[Boolean](e, List(true, false)) {

  def distance(expected: Boolean, actual: Boolean): Double = {
    if (actual == expected) 0 else 1
  }
}
