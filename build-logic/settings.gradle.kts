dependencyResolutionManagement {
  @Suppress("UnstableApiUsage")
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }
  versionCatalogs {
    create("libs") {
      from(files("../gradle/libs.versions.toml"))
    }
  }
}

rootProject.name = "spells-build-logic"
