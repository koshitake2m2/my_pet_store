import LibProject.lib
import Libraries._
import SharedProject._
import sbt.Keys.libraryDependencies
import sbt._

object AuthProject {

  lazy val auth = project
    .in(file("auth"))
    .settings(
    )
    .aggregate(authDomain, authApplication, authInfrastructure)

  lazy val authDomain = project
    .in(file("auth/domain"))
    .settings(
      libraryDependencies := commonDomain
    )
    .dependsOn(lib, sharedDomain)

  lazy val authApplication = project
    .in(file("auth/application"))
    .settings(
      libraryDependencies := commonApplication
    )
    .dependsOn(lib, authDomain, sharedDomain, sharedApplication)

  lazy val authInfrastructure = project
    .in(file("auth/infrastructure"))
    .settings(
      libraryDependencies := commonApplication ++ mysql ++ doobie
    )
    .dependsOn(lib, authDomain, authApplication, sharedDomain, sharedApplication, sharedInfrastructure)
}
