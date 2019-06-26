package cat

import printable._
import cats.Show
import cats.instances.string._

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val printableCat: Printable[Cat] =
    new Printable[Cat] {
      def format(v: Cat): String = s"${v.name} is a ${v.age} year-old ${v.color} cat"
    }

  implicit val shownCat: Show[Cat] =
    new Show[Cat] {
      def show(v: Cat): String = s"${v.name} is a ${v.age} year-old ${v.color} cat"
    }
}
