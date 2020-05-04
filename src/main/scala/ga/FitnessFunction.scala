package ga

abstract class FitnessFunction[A] {
  def evaluate(x: A): List[Double]
}