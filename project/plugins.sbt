logLevel := Level.Warn

resolvers += "Softnetwork releases" at "https://softnetwork.jfrog.io/artifactory/releases/"

addSbtPlugin("app.softnetwork.sbt-softnetwork" % "sbt-softnetwork-git" % "0.1.6")

addSbtPlugin("app.softnetwork.sbt-softnetwork" % "sbt-softnetwork-publish" % "0.1.6")

addSbtPlugin("app.softnetwork.sbt-softnetwork" % "sbt-softnetwork-protoc" % "0.1.6")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.10")
