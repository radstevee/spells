import com.google.devtools.ksp.gradle.KspAATask
import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.dokka.gradle.tasks.DokkaGeneratePublicationTask
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  id("org.jetbrains.dokka")
  id("org.jlleitschuh.gradle.ktlint")
  id("spells.publishing-conventions")
  id("net.radsteve.axi")
  id("com.google.devtools.ksp")

  `maven-publish`
  `java-library`
}

val libs = the<LibrariesForLibs>()

group = "net.radsteve.spells"
version = rootProject.property("version") as String

dependencies {
  implementation(libs.coroutines)
  ktlintRuleset(libs.ktlint.extras)
  ksp(libs.axi.ksp)
  axi.modules("core", "ui")
}

kotlin {
  jvmToolchain(21)
}

tasks.withType<KotlinCompile> {
  explicitApiMode = ExplicitApiMode.Strict

  compilerOptions {
    optIn.addAll(
      "kotlin.contracts.ExperimentalContracts",
      "kotlin.time.ExperimentalTime",
      "kotlinx.coroutines.ExperimentalCoroutinesApi",
      "kotlinx.coroutines.DelicateCoroutinesApi",
      "net.radsteve.spells.internal.InternalSpellsApi"
    )
  }

  compilerOptions.freeCompilerArgs.addAll(
    "-Xsuppress-warning=UNCHECKED_CAST",
    "-Xcontext-receivers",
  )
}

tasks.jar {
  manifest {
    attributes("Implementation-Version" to version)
  }
}

afterEvaluate {
  tasks.withType<KspAATask> {
    outputs.upToDateWhen { false }
  }
}

val dokkaJar = tasks.register<Jar>("dokkaHtmlJar") {
  dependsOn(tasks.dokkaGeneratePublicationHtml)
  from(tasks.dokkaGeneratePublicationHtml.flatMap(DokkaGeneratePublicationTask::outputDirectory))
  archiveClassifier.set("javadoc")
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])

      artifact(tasks.getByName("kotlinSourcesJar")) {
        classifier = "sources"
      }

      artifact(dokkaJar) {
        classifier = "javadoc"
      }
    }
  }
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

ktlint {
  version = "1.5.0"

  filter {
    exclude("**/generated/**")
    include("**/kotlin/**")
  }

  additionalEditorconfig.set(
    mapOf(
      "insert_final_newline" to "true",
      "end_of_line" to "lf",
      "indent_size" to "2",
      "indent_style" to "space",
      "max_line_length" to "off",
      "ktlint_function_signature_body_expression_wrapping" to "default",
      "ktlint_code_style" to "intellij_idea",
      "ktlint_experimental" to "enabled",
      "ktlint_standard_multiline-expression-wrapping" to "disabled",
      "ktlint_standard_property-wrapping" to "disabled",
      "ktlint_standard_condition-wrapping" to "disabled",
      "ktlint_standard_function-expression-body" to "disabled",
      "ktlint_standard_if-else-bracing" to "enabled",
    )
  )
}
