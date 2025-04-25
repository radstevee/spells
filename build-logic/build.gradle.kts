plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(libs.plugin.axi)
  implementation(libs.plugin.kgp)
  implementation(libs.plugin.ktlint)
  implementation(libs.plugin.dokka)
  implementation(libs.plugin.run.paper)
  implementation(libs.plugin.shadow)
  implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
