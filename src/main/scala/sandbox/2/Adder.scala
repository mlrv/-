package adder

import cats.Monoid
import cats.instances.int._
import cats.syntax.semigroup._ // for |+|

object Adder {
  def add(items: List[Int]): Int =
    items.foldLeft(Monoid[Int].empty)(_ |+| _)
}