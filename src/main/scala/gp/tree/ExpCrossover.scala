package gp.tree

abstract class ExpCrossover[A](rand: scala.util.Random) extends ga.CrossoverFunction[ga.Individual[Exp.exp[A]]](rand) {
  
  def crossoverPoint(e: Exp.exp[A]): (Exp.exp[A], (Exp.exp[A] => Exp.exp[A]))

  def binOpCrossoverPoint(f: (Exp.exp[A], Exp.exp[A]) => Exp.exp[A], a1: Exp.exp[A], a2: Exp.exp[A]): (Exp.exp[A], (Exp.exp[A] => Exp.exp[A])) = rand.nextInt(4) match {
    case 0 => (a1, x => f(x, a2))
    case 1 => (a2, x => f(a1, x))
    case 2 => {
      val (node, fun) = crossoverPoint(a1)
      (node, x => f(fun(x), a2))
    }
    case 3 => {
      val (node, fun) = crossoverPoint(a2)
      (node, x => f(a1, fun(x)))
    }
  }

}

class IntCrossover(rand: scala.util.Random) extends ExpCrossover[Long](rand) {

  def crossoverPoint(e: Exp.exp[Long]): (Exp.exp[Long], (Exp.exp[Long] => Exp.exp[Long])) = e match {
    case Exp.L(n) => (e, x => x)
    case Exp.V(n) => (e, (x => x))
    case Exp.Plus(a1, a2) => binOpCrossoverPoint((x, y) => Exp.Plus(x, y), a1, a2)
    case Exp.Minus(a1, a2) => binOpCrossoverPoint((x, y) => Exp.Minus(x, y), a1, a2)
    case Exp.Times(a1, a2) => binOpCrossoverPoint((x, y) => Exp.Times(x, y), a1, a2)
  }

  def mate(p1: ga.Individual[Exp.exp[Long]], p2: ga.Individual[Exp.exp[Long]]): ga.Individual[Exp.exp[Long]] = p1

  def mutate(p1: ga.Individual[Exp.exp[Long]]): ga.Individual[Exp.exp[Long]] = p1
}

class BoolCrossover(rand: scala.util.Random) extends ga.CrossoverFunction[ga.Individual[Exp.exp[Boolean]]](rand) {

  def mate(p1: ga.Individual[Exp.exp[Boolean]], p2: ga.Individual[Exp.exp[Boolean]]): ga.Individual[Exp.exp[Boolean]] = p1

  def mutate(p1: ga.Individual[Exp.exp[Boolean]]): ga.Individual[Exp.exp[Boolean]] = p1
}