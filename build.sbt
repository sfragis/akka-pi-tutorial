name := "akka-pi-tutorial"
description := "Akka PI Tutorial"
version := "1.0.0"
scalaVersion := "2.12.8"
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:_")
javacOptions ++= Seq("-encoding", "UTF-8")
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.12",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.12" % Test,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)
