import net.radsteve.axi.gradle.task.DependenciesJson

plugins {
  id("spells.kotlin-conventions")
}

dependencies {
  api(project(":spells-api"))
}

// We don't need any dependency json files in the default spells
tasks.withType<DependenciesJson> {
  enabled = false
}
