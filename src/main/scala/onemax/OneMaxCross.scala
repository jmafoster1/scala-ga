package onemax

class OneMaxCross(rand: scala.util.Random) extends ga.CrossoverFunction[OneMaxIndividual](rand){
  def mutate(i: OneMaxIndividual): OneMaxIndividual = {
    val inx = rand.nextInt(i.rep.length)
    val modrep = i.rep.updated(inx, !i.rep(inx))
    return new OneMaxIndividual(modrep)
  }
  
  def mate(p1: OneMaxIndividual, p2: OneMaxIndividual): OneMaxIndividual = {
      val inx = rand.nextInt(p1.rep.length)
      return new OneMaxIndividual(p1.rep.take(inx)++p2.rep.drop(inx))
  }
}