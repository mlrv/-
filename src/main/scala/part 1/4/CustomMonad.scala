package customMonad

import cats.Monad

sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

object CustomMonad {
  implicit val TreeMonad: Monad[Tree] = new Monad[Tree] {
    def pure[A](x: A): Tree[A] = Leaf(x)

    def flatMap[A, B](fa: Tree[A])(f: A => Tree[B]): Tree[B] = fa match {
      case Leaf(value) => f(value)
      case Branch(left, right) => Branch(flatMap(left)(f), flatMap(right)(f))
    }

    def tailRecM[A, B](a: A)(f: A => Tree[Either[A,B]]): Tree[B] =
      flatMap(f(a)) {
        case Left(value) => tailRecM(value)(f)
        case Right(value) => Leaf(value)
      }
  }
}