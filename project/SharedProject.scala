import LibProject.lib
import Libraries._
import sbt.Keys.libraryDependencies
import sbt._

object SharedProject {

  lazy val shared = project
    .in(file("shared"))
    .settings(
    )
    .aggregate(sharedDomain, sharedApplication, sharedInfrastructure)

  lazy val sharedDomain: Project = project
    .in(file("shared/domain"))
    .settings(
      libraryDependencies := commonDomain
    )

  lazy val sharedApplication: Project = project
    .in(file("shared/application"))
    .settings(
      libraryDependencies := commonApplication
    )
    .dependsOn(lib, sharedDomain)

  lazy val sharedInfrastructure = project
    .in(file("shared/infrastructure"))
    .settings(
      libraryDependencies := commonApplication ++ mysql ++ doobie
    )
    .dependsOn(lib, sharedDomain)

}
