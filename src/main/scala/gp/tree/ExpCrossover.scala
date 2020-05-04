package gp.tree

class ExpCrossover[A](rand: scala.util.Random) extends ga.CrossoverFunction[ga.Individual[Exp.exp[A]]](rand) {
  
  def mate(p1: ga.Individual[Exp.exp[A]],p2: ga.Individual[Exp.exp[A]]): ga.Individual[Exp.exp[A]] = ???
  
  def mutate(p1: ga.Individual[Exp.exp[A]]): ga.Individual[Exp.exp[A]] = ??? 
}