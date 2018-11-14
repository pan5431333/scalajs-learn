package webapp

import io.udash._
import io.udash.bootstrap._
import io.udash.bootstrap.alert.UdashAlert
import io.udash.bootstrap.button.{ButtonStyle, UdashButton, UdashButtonToolbar}
import io.udash.bootstrap.form.{UdashForm, UdashInputGroup}
import io.udash.bootstrap.navs.{UdashNav, UdashNavbar}
import io.udash.bootstrap.utils._
import io.udash.css.CssView._
import io.udash.properties.seq.SeqProperty
import org.scalajs.dom.html
import org.scalajs.dom.raw.Event
import scalatags.JsDom.all._

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("TutorialApp")
object TutorialApp {

  @JSExport
  def main(target: html.Div): Unit = {
    val d = UdashJumbotron(
      div(BootstrapStyles.container)(
        UdashBootstrap.loadBootstrapStyles(),
        h2("This is a button: ", color:="blue"),
        mkButton,
        h2("This is an input: ", color:="blue"),
        mkInput
      )
    ).render
    target.appendChild(d)
  }

  def mkButton: Modifier = {
    UdashButton.toggle(ButtonStyle.Primary)("Click here").render
    val buttonText = Property("Hi")
    Seq(
      b(bind(buttonText)),
      scalatags.JsDom.all.button(onclick:=((_: Event) => if (buttonText.get == "Hi") buttonText.set("Hello") else buttonText.set("Hi")))("Change")
    )
  }

  def mkInput: Modifier = {
    Seq(UdashForm.textInput()("Type your text here: ")(Property.blank),
    UdashForm.fileInput()("Upload your file here. ")("CV file", Property(false), SeqProperty.blank),
    UdashForm.passwordInput()("Type your password here. ")(Property.blank)
    )
  }
}