package ga

abstract class FitnessFunction[A] {
  def evaluate(x: A): List[Double]

  def rmsd(errors: List[Double]): Double = {
    var sum: Double = 0D
    for (d <- errors) {
      sum += (d * d)
    }
    val mean: Double = sum / errors.size
    Math.sqrt(mean)
  }

}