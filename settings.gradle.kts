rootProject.name = "spells"

pluginManagement {
  includeBuild("build-logic")
}

setOf(
  "api",
  "plugin",
  "spells",
).forEach { mod ->
  include(":spells-$mod")
  project(":spells-$mod").projectDir = file(mod)
}
