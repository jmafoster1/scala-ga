package onemax

class OneMaxFit extends ga.FitnessFunction[OneMaxIndividual] {
  def evaluate(i: OneMaxIndividual):List[Double] = {
    List(i.rep.filter(x => x).length)
  }
}