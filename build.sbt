libraryDependencies ++= {
  Seq(
    "org.scala-lang" % "scala-compiler" % "2.8.1",
    "org.scala-lang" % "scala-swing" % "2.11+",
    "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
  )
}

organization := "net.raymondoyondi"
scalaVersion := "3.8.1"
scalaSource in Compile := baseDirectory.value / "src"
scalaSource in Test := baseDirectory.value / "test"

lazy val root = (project in file(".")).
  settings(
    name := "Scaltris"
  )
