package safeEvalFold

import cats.Eval

object SafeEvalFold {
  def foldRight[A, B](as: List[A], acc: B)(f: (A, B) => B): B =
    as match {
      case head :: tail => f(head, foldRight(tail, acc)(f))
      case Nil          => acc
    }

    def safeFoldRight[A, B](as: List[A], acc: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] =
      as match {
        case head :: tail => Eval.defer(f(head, foldRight(tail, acc)(f)))
        case Nil          => acc
      }

}