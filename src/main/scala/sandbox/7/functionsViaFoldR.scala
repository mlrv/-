package functionsViaFoldR

import scala.language.implicitConversions

object FunctionsViaFoldR {
  implicit def listToEnrichedList[A](l: List[A]) = new EnrichedList(l)

  case class EnrichedList[A](targetList: List[A]) {
    def mapR[B](f: A => B): List[B] =
      targetList.foldRight(List.empty[B])((i, a) => f(i) :: a)
    
    def filterR(f: A => Boolean): List[A] =
      targetList.foldRight(List.empty[A])((i, a) => if (f(i)) i :: a else a)
    
    def flatMapR[B](f: A => List[B]): List[B] =
      targetList.foldRight(List.empty[B])((i, a) => f(i) ::: a)
  }
}