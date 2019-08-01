package booleanMonoid

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

object BooleanMonoid {
  implicit val andMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def empty = true
      def combine(a: Boolean, b: Boolean) = a && b
    }

  implicit val orMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def empty = false
      def combine(a: Boolean, b: Boolean) = a || b
    }
}