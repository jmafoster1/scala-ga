package onemax
import scala.util.Random

class OneMaxIndividual(r: List[Boolean]) extends ga.Individual with Ordered[OneMaxIndividual] {
  
  val rep = r
  
  override def toString(): String = rep.map(x => if (x) "1" else "0").mkString("") + ":" + fitness.head
  
  def compare(that: OneMaxIndividual): Int = {
    return compare_lists(this.fitness, that.fitness)
  }
  
}