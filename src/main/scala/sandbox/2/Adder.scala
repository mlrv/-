package adder

import cats.Monoid
import cats.instances.int._
import cats.instances.option._
import cats.syntax.semigroup._ // for |+|

object Adder {
  def add[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.foldLeft(monoid.empty)(_ |+| _)

  def optionAdd(items: List[Option[Int]]): Option[Int] =
    add(items)(Monoid[Option[Int]])
}