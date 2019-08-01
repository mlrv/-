package cat

import printable._

import cats.Show
import cats.Eq
import cats.syntax.eq._
import cats.instances.string._ 
import cats.instances.int._ 

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

  implicit  val eqCat: Eq[Cat] =
    Eq.instance((c1, c2) => {
      c1.name  ===  c2.name  && 
      c1.color ===  c2.color &&
      c1.age   ===  c2.age
    })
}
