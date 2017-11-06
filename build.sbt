name := "training-db"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++={
  Seq(
    "com.typesafe.play" %% "play-json" % "2.6.0-M1",
    "com.typesafe.akka" %% "akka-http" % "10.0.10",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.10",
    "com.typesafe.akka" %% "akka-stream" % "2.5.4",
    "com.typesafe.akka" %% "akka-actor"  % "2.5.4",
    "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"
  )
}
        