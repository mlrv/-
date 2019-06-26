package printable

trait Printable[A] {
  def format(v: A): String
}

object PrintableInstances {
  implicit val printableInt: Printable[Int] =
    new Printable[Int] {
      def format(v: Int): String = v.toString()
    }

  implicit val printableString: Printable[String] =
    new Printable[String] {
      def format(v: String): String = v
    }
}

object Printable {
  def format[A](v: A)(implicit p: Printable[A]): String =
    p.format(v)
  
  def print[A](v: A)(implicit p: Printable[A]): Unit =
    println(format(v)) 
}