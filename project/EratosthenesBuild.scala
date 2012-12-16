import sbt._
import sbt.Keys._

object EratosthenesBuild extends Build {

  lazy val eratosthenes = Project(
    id = "eratosthenes",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "eratosthenes",
      organization := "org.LambdaKai",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.9.2"
      // add other settings here
    )
  )
}
