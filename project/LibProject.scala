import Libraries.commonDomain
import sbt.Keys.libraryDependencies
import sbt._

object LibProject {
  lazy val lib: Project = project
    .in(file("lib"))
    .settings(
      libraryDependencies := commonDomain
    )
}
