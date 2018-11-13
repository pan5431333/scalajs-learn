name := "scalajs-learn"

version := "0.1"

val scalaV = "2.12.6"

import sbt.io.Path.rebase
import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

val shared = (crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure) in file("shared")) // [Pure, Full, Dummy], default: CrossType.Full
  .settings(scalaVersion := scalaV)

val sharedJvm = shared.jvm
val sharedJs = shared.js

lazy val client = (project in file("client"))
  .settings(scalaVersion := scalaV)
  .settings(libraryDependencies += "com.lihaoyi" %%% "scalatags" % "0.6.7")
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(sharedJs)

lazy val copyJSCode = taskKey[Seq[File]]("Copy the JS code generated by ScalaJS to server project. ")
copyJSCode := {
  val files: Seq[File] = (target in(client, Compile)).value.**("*.js").get
  val oldBase: File = (target in (client, Compile)).value / "scala-2.12"
  val newBase: File = baseDirectory(_ / "server" / "public" / "javascripts").value
  val mappings: Seq[(File, File)] = files pair rebase(oldBase, newBase)
  println(s"Copying JavaScript code to $newBase")
  IO.copy(mappings).toSeq
}
copyJSCode := copyJSCode.dependsOn(fastOptJS in (client, Compile)).value

compile in (server, Compile) := (compile in (server, Compile)).dependsOn(copyJSCode).value

lazy val server = (project in file("server"))
  .settings(scalaVersion := scalaV)
  .settings(libraryDependencies += guice)
  .enablePlugins(PlayScala)
  .dependsOn(sharedJvm)


