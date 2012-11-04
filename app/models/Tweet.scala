package models

import anorm._
import anorm.SqlParser._
import java.util.Date
import org.joda.time._
import play.api.db._
import play.api.Play._

case class Tweet(
  id: Long, content: String, createdAt: Date)

object Tweet {

  val simple = {
    get[Long]("id") ~
    get[String]("content") ~
    get[Date]("created_at") map {
      case id~content~createdAt =>
        Tweet(id,content,createdAt)
    }
  }

  def all(): List[Tweet] = {
    DB.withConnection { implicit c =>
      SQL(
        "select * from tweet order by created_at desc"
      ).as(simple *)
    }
  }

  def create(content: String) {
    DB.withConnection { implicit c =>
      SQL("""
          insert into tweet(content,created_at)
          values ({content},{created_at})
      """).on(
        'content -> content,
        'created_at -> DateTime.now().toDate()
      ).executeUpdate()
    }
  }
}