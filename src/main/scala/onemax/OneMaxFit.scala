package onemax

class OneMaxFit extends ga.FitnessFunction[ga.Individual[List[Boolean]]] {
  def evaluate(i: ga.Individual[List[Boolean]]):List[Double] = {
    List(i.rep.filter(x => x).length)
  }
}