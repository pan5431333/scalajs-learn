logLevel := Level.Warn
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.15") // Play SBT plugin 
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.7") // Plugin for package fat jar 
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.24") // ScalaJS SBT plugin 
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "0.5.0") // Plugin for sharing code between ScalaJS and Scala 