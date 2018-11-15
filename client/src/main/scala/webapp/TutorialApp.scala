package webapp

import io.udash._
import io.udash.bootstrap._
import io.udash.bootstrap.alert.UdashAlert
import io.udash.bootstrap.button.{ButtonSize, ButtonStyle, UdashButton, UdashButtonToolbar}
import io.udash.bootstrap.form.{UdashForm, UdashInputGroup}
import io.udash.bootstrap.navs.{UdashNav, UdashNavbar}
import io.udash.bootstrap.utils._
import io.udash.css.CssView._
import io.udash.properties.seq.SeqProperty
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.html
import org.scalajs.dom.raw.Event
import scalatags.JsDom.all._

import scala.concurrent.Await
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.util.{Failure, Success}

@JSExportTopLevel("TutorialApp")
object TutorialApp {

  @JSExport
  def main(target: html.Div): Unit = {
    val d = UdashJumbotron(
      div(BootstrapStyles.container)(
        UdashBootstrap.loadBootstrapStyles(),
        h2("This is a button: ", color := "blue"),
        mkButton,
        h2("This is an input: ", color := "blue"),
        mkInput,
        h2("Click here to display Baidu.com", color := "blue"),
        mkBaidu
      )
    ).render
    target.appendChild(d)
  }


  def mkButton: Modifier = {
    val buttonText = Property("Hi")
    Seq(
      b(bind(buttonText)).render, br.render,
      UdashButton.toggle(ButtonStyle.Primary)("Click here", onclick := ((_: Event) => if (buttonText.get == "Hi") buttonText.set("Hello") else buttonText.set("Hi"))).render
    )
  }

  def mkInput: Modifier = {
    Seq(UdashForm.textInput()("Type your text here: ")(Property.blank),
      UdashForm.fileInput()("Upload your file here. ")("CV file", Property(false), SeqProperty.blank),
      UdashForm.passwordInput()("Type your password here. ")(Property.blank)
    )
  }

  def mkBaidu: Modifier = {
    val baiduPage: Property[Any] = Property.blank
    val loadBaidu: Event => Unit = (_: Event) => {
      import scala.concurrent.ExecutionContext.Implicits.global
      val baiduFuture = Ajax.get("http://www.tencent.com")
      baiduFuture.onComplete{
        case Success(baidu) => baiduPage.set(baidu)
        case Failure(e) => baiduPage.set(e.getMessage + e.getStackTrace.mkString(", "))
      }
    }

    Seq(
      UdashButton.toggle(buttonStyle = ButtonStyle.Primary, size = ButtonSize.Large)("load Baidu", onclick := loadBaidu).render,
      UdashButton.toggle(ButtonStyle.Danger)("Clear", onclick:=((_: Event) => baiduPage.set(null))).render,
      p(bind(baiduPage)).render
    )
  }
}