package functionsViaFoldR

import scala.language.implicitConversions

object FunctionsViaFoldR {
  implicit def listToEnrichedList[A](l: List[A]) = new EnrichedList(l)

  case class EnrichedList[A](targetList: List[A]) {
    def mapR[B](f: A => B): EnrichedList[B] =
      targetList.foldRight(List.empty[B])((i, a) => f(i) :: a)
  }
}