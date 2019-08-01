package product

import cats.Monad
import cats.syntax.flatMap._
import cats.syntax.functor._

object Product {
  def product[M[_]: Monad, A, B](a: M[A], b: M[B]): M[(A, B)] =
    for {
      x <- a
      y <- b
    } yield (x, y)
}