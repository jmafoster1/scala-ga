package gp.tree

object Exp {

  type Context = Map[vname, Object]

  abstract sealed class vname(latent: Boolean) {
    def eval(c: Context): Object = {
      c(this)
    }
  }
  final case class I[A](n: Int) extends vname(false)
  final case class R[A](n: Int, latent: Boolean) extends vname(latent)

  abstract sealed class aexp[A] {
    def eval(c: Context): A
  }
  final case class L[A](a: A) extends aexp[A] {
    def eval(c: Context): A = a
  }
  final case class V[A](a: vname) extends aexp[A] {
    def eval(c: Context): A = c(a).asInstanceOf[A]
  }
  final case class Plus(a: aexp[Int], b: aexp[Int]) extends aexp[Int] {
    def eval(c: Context): Int = a.eval(c) + b.eval(c)
  }
  final case class Minus[A](a: aexp[Int], b: aexp[Int]) extends aexp[Int] {
    def eval(c: Context): Int = a.eval(c) - b.eval(c)
  }
  final case class Times[A](a: aexp[Int], b: aexp[Int]) extends aexp[Int] {
    def eval(c: Context): Int = a.eval(c) * b.eval(c)
  }

  final case class Bc[A](a: Boolean) extends aexp[Boolean] {
    def eval(c: Context) = a
  }
  final case class Eq[A](a: aexp[A], b: aexp[A]) extends aexp[Boolean] {
    def eval(c: Context) = (a.eval(c) == b.eval(c))
  }
  final case class Gt[A <% Ordered[A]](a: aexp[A], b: aexp[A]) extends aexp[Boolean] {
    def eval(c: Context) = (a.eval(c) > b.eval(c))
  }
  final case class And[A](a: aexp[Boolean], b: aexp[Boolean]) extends aexp[Boolean] {
    def eval(c: Context) = (a.eval(c) && b.eval(c))
  }
  final case class Or[A](a: aexp[Boolean], b: aexp[Boolean]) extends aexp[Boolean] {
    def eval(c: Context) = (a.eval(c) || b.eval(c))
  }
  final case class Not[A](a: aexp[Boolean]) extends aexp[Boolean] {
    def eval(c: Context) = !a.eval(c)
  }
}
