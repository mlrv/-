package formValidation

import cats.data.Validated
import cats.syntax.either._ // for catchOnly
import cats.instances.list._ // for Semigroupal
import cats.syntax.apply._ // for mapN

object FormValidation {
  type FormData = Map[String, String]
  type FailFast[A] = Either[List[String], A]
  type FailSlow[A] = Validated[List[String], A]

  case class User(name: String, age: Int)

  def readUser(input: FormData): FailSlow[User] =
    (
      readName(input).toValidated,
      readAge(input).toValidated
    ).mapN(User.apply)

  def readName(input: Map[String, String]): FailFast[String] = 
    getValue(input, "name").flatMap(nonBlank)

  def readAge(input: Map[String, String]): FailFast[Int] =
    getValue(input, "age")
      .flatMap(nonBlank)
      .flatMap(parseInt)  
      .flatMap(nonNegative)

  def getValue(map: FormData, fieldName: String): FailFast[String] =
    map.get(fieldName).toRight(List(s"$fieldName not specified"))

  def parseInt(str: String): FailFast[Int] =
    Either
      .catchOnly[NumberFormatException](str.toInt)
      .leftMap(_ => List("Value must be an integer"))
    
  def nonBlank(str: String): FailFast[String] =
    Right(str).ensure(List("can't be blank"))(_.nonEmpty)

  def nonNegative(int: Int): FailFast[Int] =
    Right(int).ensure(List("can't be negative"))(_ > 0) 

}