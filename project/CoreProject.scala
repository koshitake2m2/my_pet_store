import AuthProject.{authApplication, authInfrastructure}
import LibProject.lib
import Libraries._
import SharedProject._
import sbt.Keys._
import sbt._
import sbtassembly.AssemblyKeys.assembly

object CoreProject {

  lazy val core = project
    .in(file("core"))
    .settings(
    )
    .aggregate(coreDomain, coreApplication, coreInfrastructure)

  lazy val coreDomain = project
    .in(file("core/domain"))
    .settings(
      libraryDependencies := commonDomain
    )
    .dependsOn(lib, sharedDomain)

  lazy val coreApplication = project
    .in(file("core/application"))
    .settings(
      libraryDependencies := commonApplication
    )
    .dependsOn(lib, coreDomain, sharedDomain, sharedApplication)

  lazy val coreInfrastructure = project
    .in(file("core/infrastructure"))
    .settings(
      assembly / mainClass := Some("com.github.koshitake2m2.petstorees.core.web.WebServer"),
      libraryDependencies := commonApplication ++ http4s ++ mysql ++ doobie ++ mongodb
    )
    .dependsOn(
      lib,
      coreDomain,
      coreApplication,
      sharedDomain,
      sharedApplication,
      sharedInfrastructure,
      authApplication,
      authInfrastructure
    )

}
