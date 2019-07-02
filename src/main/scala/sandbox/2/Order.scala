package order

import cats.Monoid

case class Order(totalCost: Double, quantity: Double)

object MonoidInstances {
  implicit val monoidOrder: Monoid[Order] =
    new Monoid[Order] {
      def empty: Order = Order(0, 0)
      def combine(x: Order, y: Order): Order =
        Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
    }
}