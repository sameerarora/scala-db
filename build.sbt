name := "training-db"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++={
  Seq(
    "com.typesafe.play" %% "play-json" % "2.6.0-M1",
    "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"
  )
}
        