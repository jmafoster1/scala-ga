package gp.tree

import scala.util.Random

class ExpIndividual[A <% Ordered[A]](tree: Exp.aexp[A]) extends ga.Individual with Ordered[ExpIndividual[A]] {
  
  def compare(that: ExpIndividual[A]): Int = {
    return compare_lists(this.fitness, that.fitness)
  }
  
}