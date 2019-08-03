package mapReduce

import cats.Monoid
import cats.syntax.semigroup._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


object MapReduce {

  def foldMap[A, B: Monoid](seq: Vector[A])(f: A => B): B =
    seq.map(f).foldLeft(Monoid.empty)(_ |+| _)

  def parallelFoldMap[A, B: Monoid](seq: Vector[A])(f: A => B): Future[B] = {
    // Calculate the number of items to pass to each CPU
    val cores = Runtime.getRuntime.availableProcessors
    val groupSize = (1.0 * seq.size / cores).ceil.toInt

    // Create one group for each CPU
    val groups: Iterator[Vector[A]] = seq.grouped(groupSize)

    // Create a future to foldMap each group
    val futures: Iterator[Future[B]] = 
      groups.map(group => Future(foldMap(group)(f)))

    Future.sequence(futures).map { iterable =>
      iterable.foldLeft(Monoid[B].empty)(_ |+| _)
    }
  }

}