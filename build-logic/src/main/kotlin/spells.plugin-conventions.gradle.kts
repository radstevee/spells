plugins {
  id("spells.kotlin-conventions")
  id("xyz.jpenilla.run-paper")
  id("com.gradleup.shadow")
}

val shade = configurations.create("shade")

tasks {
  assemble {
    dependsOn(shadowJar)
  }

  shadowJar {
    version = ""
    archiveClassifier = ""
    configurations = setOf(shade)
  }

  runServer {
    minecraftVersion("1.21.4")
  }
}
