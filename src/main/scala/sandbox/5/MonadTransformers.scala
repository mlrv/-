package monadTransformers

import scala.concurrent.Future
import cats.data.{EitherT}
import cats.instances.future._
import scala.concurrent.ExecutionContext.Implicits.global

object MonadTransformers {
  type Response[A] = EitherT[Future, String, A]
  val powerLevels = Map(
    "Jazz"      -> 6,
    "Bumblebee" -> 8,
    "Hot Rod"   -> 10
  )
  def getPowerLevel(autobot: String): Response[Int] =
    powerLevels.get(autobot) match {
      case Some(value) => EitherT.right(Future(value))
      case None        => EitherT.left(Future("Not found"))
    }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] =
    for {
      fst <- getPowerLevel(ally1)
      snd <- getPowerLevel(ally2)
    } yield (fst + snd) > 15

}