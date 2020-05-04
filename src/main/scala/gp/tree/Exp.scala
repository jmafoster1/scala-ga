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

  abstract sealed class exp[A] {
    def eval(c: Context): A
  }
  final case class L[A](a: A) extends exp[A] {
    def eval(c: Context): A = a
  }
  final case class V[A](a: vname) extends exp[A] {
    def eval(c: Context): A = c(a).asInstanceOf[A]
  }
  final case class Plus(a: exp[Int], b: exp[Int]) extends exp[Int] {
    def eval(c: Context): Int = a.eval(c) + b.eval(c)
  }
  final case class Minus[A](a: exp[Int], b: exp[Int]) extends exp[Int] {
    def eval(c: Context): Int = a.eval(c) - b.eval(c)
  }
  final case class Times[A](a: exp[Int], b: exp[Int]) extends exp[Int] {
    def eval(c: Context): Int = a.eval(c) * b.eval(c)
  }

  final case class Bc[A](a: Boolean) extends exp[Boolean] {
    def eval(c: Context) = a
  }
  final case class Eq[A](a: exp[A], b: exp[A]) extends exp[Boolean] {
    def eval(c: Context) = (a.eval(c) == b.eval(c))
  }
  final case class Gt[A <% Ordered[A]](a: exp[A], b: exp[A]) extends exp[Boolean] {
    def eval(c: Context) = (a.eval(c) > b.eval(c))
  }
  final case class And[A](a: exp[Boolean], b: exp[Boolean]) extends exp[Boolean] {
    def eval(c: Context) = (a.eval(c) && b.eval(c))
  }
  final case class Or[A](a: exp[Boolean], b: exp[Boolean]) extends exp[Boolean] {
    def eval(c: Context) = (a.eval(c) || b.eval(c))
  }
  final case class Not[A](a: exp[Boolean]) extends exp[Boolean] {
    def eval(c: Context) = !a.eval(c)
  }
}
