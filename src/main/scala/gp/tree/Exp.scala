package gp.tree

object Exp {

  type Context = Map[vname, Object]

  abstract sealed class vname(l: Boolean) {
    val latent = l
    def eval(c: Context): Object = {
      c(this)
    }
  }
  final case class I[A](n: Int) extends vname(false) {
    override def toString() = f"i$n"
  }
  final case class R[A](n: Int, l: Boolean = false) extends vname(l) {
    override def toString() = f"r$n"
  }

  abstract sealed class exp[A] {
    def eval(c: Context): A
    def height(): Int
    def varsInTree(): Set[vname]
    def latentVars = varsInTree.filter(x => x.latent)
  }
  final case class L[A](a: A) extends exp[A] {
    def eval(c: Context): A = a
    def height = 1
    def varsInTree = Set()
    override def toString() = a.toString()
  }
  final case class V[A](a: vname) extends exp[A] {
    def eval(c: Context): A = c(a).asInstanceOf[A]
    def height = 1
    def varsInTree = Set(a)
    override def toString() = a.toString()
  }
  final case class Plus(a: exp[Long], b: exp[Long]) extends exp[Long] {
    def eval(c: Context): Long = a.eval(c) + b.eval(c)
    def height = Math.max(a.height, b.height)
    def varsInTree = a.varsInTree().union(b.varsInTree())
    override def toString() = f"($a + $b)"
  }
  final case class Minus[A](a: exp[Long], b: exp[Long]) extends exp[Long] {
    def eval(c: Context): Long = a.eval(c) - b.eval(c)
    def height = Math.max(a.height, b.height)
    def varsInTree = a.varsInTree().union(b.varsInTree())
    override def toString() = f"($a - $b)"
  }
  final case class Times[A](a: exp[Long], b: exp[Long]) extends exp[Long] {
    def eval(c: Context): Long = a.eval(c) * b.eval(c)
    def height = Math.max(a.height, b.height)
    def varsInTree = a.varsInTree().union(b.varsInTree())
    override def toString() = f"($a * $b)"
  }
  final case class Bc[A](a: Boolean) extends exp[Boolean] {
    def eval(c: Context) = a
    def height = 1
    def varsInTree = Set()
    override def toString() = a.toString()
  }
  final case class Eq[A](a: exp[A], b: exp[A]) extends exp[Boolean] {
    def eval(c: Context) = (a.eval(c) == b.eval(c))
    def height = Math.max(a.height, b.height)
    def varsInTree = a.varsInTree().union(b.varsInTree())
    override def toString() = f"$a = $b"
  }
  final case class Gt[A <% Ordered[A]](a: exp[A], b: exp[A]) extends exp[Boolean] {
    def eval(c: Context) = (a.eval(c) > b.eval(c))
    def height = Math.max(a.height, b.height)
    def varsInTree = a.varsInTree().union(b.varsInTree())
    override def toString() = f"$a > $b"
  }
  final case class And(a: exp[Boolean], b: exp[Boolean]) extends exp[Boolean] {
    def eval(c: Context) = (a.eval(c) && b.eval(c))
    def height = Math.max(a.height, b.height)
    def varsInTree = a.varsInTree().union(b.varsInTree())
    override def toString() = f"$a && $b"
  }
  final case class Or(a: exp[Boolean], b: exp[Boolean]) extends exp[Boolean] {
    def eval(c: Context) = (a.eval(c) || b.eval(c))
    def height = Math.max(a.height, b.height)
    def varsInTree = a.varsInTree().union(b.varsInTree())
    override def toString() = f"$a || $b"
  }
  final case class Not(a: exp[Boolean]) extends exp[Boolean] {
    def eval(c: Context) = !a.eval(c)
    def height = 1 + a.height()
    def varsInTree = a.varsInTree()
    override def toString() = f"!($a)"
  }
}
