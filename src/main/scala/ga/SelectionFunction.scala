package ga

abstract class SelectionFunction[A] {
  def select(population: List[A]): (A, A)
}