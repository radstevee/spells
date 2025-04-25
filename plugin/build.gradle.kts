plugins {
  id("spells.plugin-conventions")
}

dependencies {
  shade(implementation(project(":spells-spells"))!!)
}
