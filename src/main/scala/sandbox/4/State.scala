package statePackage

import cats.data.State
import cats.syntax.applicative._

object StatePackage {
  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case num => operand(num.toInt)
  }

  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState]) { (a, b) =>
      a.flatMap(_ => evalOne(b))
    }

  // Push number onto the stack
  def operand(num: Int): CalcState[Int] =
    State[List[Int], Int] { stack =>
      (num :: stack, num)
    }

  // Pop two items off the stack, evaluate the operation, and push the result onto the stack
  def operator(f: (Int, Int) => Int): CalcState[Int] =
    State[List[Int], Int] {
      case b :: a :: tail =>
        val ans = f(a, b)
        (ans :: tail, ans)
      case _ =>
        sys.error("Not enough items on the stack")
    }
  
}