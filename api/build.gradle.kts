import net.radsteve.axi.gradle.task.DependenciesJson

plugins {
  id("spells.kotlin-conventions")
}

// We don't need any dependency json files in the API
tasks.withType<DependenciesJson> {
  enabled = false
}
