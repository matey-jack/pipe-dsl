version := "0.1.0"

scalaVersion := "2.11.7"

// resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-java-sdk-datapipeline" % "1.10.27",
  "org.scalatest" % "scalatest_2.11" % "2.2.5" % "test",
  "codes.reactive" %% "scala-time" % "0.1.0-RC1"
)


