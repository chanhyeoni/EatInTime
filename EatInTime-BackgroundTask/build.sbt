name := "EatInTime-BackgroundTask"
version := "1.0"
scalaVersion := "2.11.8"

//javacOptions ++= Seq(“-source”, “1.8”, “-target”, “1.8”, “-Xlint”)


libraryDependencies ++= Seq(
	"org.joda" % "joda-convert" % "1.5",
	"org.scala-lang" % "scala-library" % "2.11.8",
	"io.netty" % "netty-all" % "4.0.18.Final",
	"org.apache.spark" % "spark-core_2.11" % "2.1.0",
	"org.apache.spark" % "spark-sql_2.11" % "2.1.0",
	"org.apache.spark" % "spark-mllib_2.11" % "2.1.0",
	"org.mongodb" %% "casbah" % "3.1.1",
	"org.mongodb.spark"	% "mongo-spark-connector_2.11" % "2.0.0",
	"org.scalaj" %% "scalaj-http" % "2.3.0",
	"com.twilio.sdk" % "twilio" % "7.1.0"
	//"systems.fail-fast" %% "twilio-scala" % "0.2"
	//"org.scala-debugger" %% "scala-debugger-api" % "1.1.0-M3"
)

//addSbtPlugin("org.scala-debugger" % "sbt-jdi-tools" % "1.0.0")