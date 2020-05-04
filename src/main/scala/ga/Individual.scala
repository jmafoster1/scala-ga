package ga

class Individual[A](r: A) extends Ordered[Individual[A]]{
  
  var fitness: List[Double] = null
  val rep = r
  
  def compare_lists(l1: List[Double], l2: List[Double]): Int = (l1, l2) match {
    case (Nil, Nil) => 0
    case (Nil, _) => -1
    case (_, Nil) => 1
    case (h1::t1, h2::t2) => {
      if (h1 == h2)
        compare_lists(t1, t2)
      else
        h1.compare(h2)
    }
  }
  
  def compare(that: Individual[A]): Int = {
    return compare_lists(this.fitness, that.fitness)
  }
  
}

