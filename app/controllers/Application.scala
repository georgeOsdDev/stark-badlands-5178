package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._


object Application extends Controller {
  
  val tweetForm = Form(
    "content" -> nonEmptyText(maxLength = 140)
  )

  def index = Action {
    Ok(views.html.index(tweetForm,Tweet.all()))
  }
  
  def tweet = Action { implicit request =>
    tweetForm.bindFromRequest.fold(
      errors => BadRequest(
        views.html.index(errors,Tweet.all())),
      content => {
        Tweet.create(content)
        Redirect(routes.Application.index)
        }
    )
  }

  def login = TODO
  
  def auth = TODO
}