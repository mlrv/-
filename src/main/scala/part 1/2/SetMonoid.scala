package setMonoid

trait Semigroup[A] {
  def combine(x: A, y: A): A
}
trait Monoid[A] extends Semigroup[A] {
  def empty: A
}
object Monoid {
  def apply[A](implicit monoid: Monoid[A]) =
    monoid
}

object SetMonoid {
  implicit def unionMonod[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      def empty = Set.empty
      def combine(a: Set[A], b: Set[A]) = a ++ b
    }
}