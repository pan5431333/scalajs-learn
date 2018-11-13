package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import views.html

class MainController @Inject()(val cc: ControllerComponents)
  extends AbstractController(cc)
    with play.api.i18n.I18nSupport {

  def index = Action {
    Ok(html.TestPage())
  }

}
