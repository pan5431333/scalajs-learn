package webapp
import org.scalajs.dom.html
import scalatags.JsDom.all._

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("TutorialApp")
object TutorialApp {

  @JSExport
  def test(target:html.Div):Unit={
    val name=("潘盟","Meng Pan")
    val d = div(
      h1("Hello World!",color:="blue", fontFamily:="Monaco", fontSize:="14px"),
      p(s"my name is ${name._1}"),
      p(s"also,call me ${name._2}"),
      p("Greetings from Meng PAN", color:="blue", fontFamily:="Monaco", fontSize:="14px")
    ).render
    target.appendChild(d)
  }

}