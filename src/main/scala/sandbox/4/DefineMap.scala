package defineMap

import scala.language.higherKinds

trait Monad[F[_]] {
  def pure[A](a: A): F[A]
  def flatMap[A, B](a: F[A])(f: A => F[B]): F[B]
  def map[A, B](a: F[A])(f: A => B): F[B] =
    flatMap(a)(x => pure(f(x)))
}