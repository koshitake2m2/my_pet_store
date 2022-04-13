ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "0.1.0"

/** scala options.
  *
  * reference:
  *   - scala2 reference: https://docs.scala-lang.org/overviews/compiler-options/index.html
  *   - scala3 reference: https://docs.scala-lang.org/scala3/guides/migration/options-intro.html
  */
ThisBuild / scalacOptions ++= Seq(
  "-Xfatal-warnings", // warnがあるとコンパイルが失敗する
  "-deprecation", // @deprecated(非推奨)があるとwarnになる
  "-unchecked", // @unchecked(パターンマッチの不備を許容)があるとwarnになる
  "-language:implicitConversions", // implicit conversionの定義を許容する
  "-language:higherKinds", // 高カインド型の定義を許容する
  "-Xsource:3", // scala3の一部のsyntaxが利用可能
  "-Xlint", // 推奨される警告が表示されるようになる
  "-Xlint:-byname-implicit", // by-nameによるimplicitを許容する
  "-Xlint:-package-object-classes", // package objectにクラスの定義を許容する
  "-Ymacro-annotations" // newtypeを利用するためにmacro annotationを許容する
)

lazy val root = project
  .in(file("."))
  .aggregate(lib, shared, core, auth)

lazy val lib = LibProject.lib

lazy val shared = SharedProject.shared
lazy val sharedDomain = SharedProject.sharedDomain
lazy val sharedApplication = SharedProject.sharedApplication
lazy val sharedInfrastructure = SharedProject.sharedInfrastructure

lazy val auth = AuthProject.auth
lazy val authDomain = AuthProject.authDomain
lazy val authApplication = AuthProject.authApplication
lazy val authInfrastructure = AuthProject.authInfrastructure

lazy val core = CoreProject.core
lazy val coreDomain = CoreProject.coreDomain
lazy val coreApplication = CoreProject.coreApplication
lazy val coreInfrastructure = CoreProject.coreInfrastructure
