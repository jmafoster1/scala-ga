package gp.tree

class ExpCrossover[A](rand: scala.util.Random) extends ga.CrossoverFunction[ExpIndividual[A]](rand) {
  
  def mate(p1: gp.tree.ExpIndividual[A],p2: gp.tree.ExpIndividual[A]): gp.tree.ExpIndividual[A] = ???
  
  def mutate(p1: gp.tree.ExpIndividual[A]): gp.tree.ExpIndividual[A] = ??? 
}