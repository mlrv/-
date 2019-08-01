package adder

import cats.Monoid
import cats.syntax.semigroup._ // for |+|

object Adder {
  def add[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.foldLeft(monoid.empty)(_ |+| _)
}