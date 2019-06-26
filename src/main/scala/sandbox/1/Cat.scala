package cat

import printable._

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val printableCat: Printable[Cat] =
    new Printable[Cat] {
      def format(v: Cat): String = s"${v.name} is a ${v.age} year-old ${v.color} cat"
    }
}
