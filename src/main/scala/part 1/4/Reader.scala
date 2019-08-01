package readerPackage

import cats.data.Reader
import cats.syntax.applicative._

case class Db(usernames: Map[Int, String], passwords: Map[String, String])

object ReaderPackage {
  type DbReader[A] = Reader[Db, A]

  def findUsername(id: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(id))

  def checkPassword(
    username: String,
    password: String
  ): DbReader[Boolean] =
    Reader(db => db.passwords.get(username) match {
      case Some(x) => x == password
      case       _ => false
    })

  def checkLogin(
    id: Int,
    password: String
  ): DbReader[Boolean] =
    for {
      username <- findUsername(id)
      passwordOk <- username.map {
        u => checkPassword(u, password)
      }.getOrElse { false.pure[DbReader] }
    } yield passwordOk
}