package onemax

class OneMaxCross(rand: scala.util.Random) extends ga.CrossoverFunction[ga.Individual[List[Boolean]]](rand){
  def mutate(i: ga.Individual[List[Boolean]]): ga.Individual[List[Boolean]] = {
    val inx = rand.nextInt(i.rep.length)
    val modrep = i.rep.updated(inx, !i.rep(inx))
    return new ga.Individual[List[Boolean]](modrep)
  }
  
  def mate(p1: ga.Individual[List[Boolean]], p2: ga.Individual[List[Boolean]]): ga.Individual[List[Boolean]] = {
      val inx = rand.nextInt(p1.rep.length)
      return new ga.Individual[List[Boolean]](p1.rep.take(inx)++p2.rep.drop(inx))
  }
}