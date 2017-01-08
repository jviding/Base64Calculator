import spray.revolver.RevolverPlugin.Revolver
import com.typesafe.sbt.SbtStartScript

organization  := "com.example"

name := "calculate"

version       := "0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV,
    "io.spray"            %%  "spray-json"    % "1.3.1",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV,
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test"
  )
}

seq(SbtStartScript.startScriptForClassesSettings: _*)

seq(Revolver.settings: _*)