import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.kordamp.gradle.plugin.markdown.tasks.MarkdownToHtmlTask

plugins {
  // Custom plugin for building all of the themes
  id("doki-theme-plugin")
  // Kotlin support
  id("org.jetbrains.kotlin.jvm") version "1.4.21-2"
  // gradle-intellij-plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
  id("org.jetbrains.intellij") version "0.6.5"
  // detekt linter - read more: https://detekt.github.io/detekt/gradle.html
  id("io.gitlab.arturbosch.detekt") version "1.15.0"
  // ktlint linter - read more: https://github.com/JLLeitschuh/ktlint-gradle
  id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
  id("org.kordamp.gradle.markdown") version "2.2.0"
}

// Import variables from gradle.properties file
val pluginGroup: String by project
val pluginVersion: String by project
val pluginSinceBuild: String by project
val pluginUntilBuild: String by project

val platformType: String by project
val platformVersion: String by project
val platformPlugins: String by project
val platformDownloadSources: String by project

val idePath: String by project

group = pluginGroup
version = pluginVersion

// Configure project's dependencies
repositories {
  mavenCentral()
  jcenter()
  mavenLocal()
}

dependencies {
  detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.15.0")
  implementation("commons-io:commons-io:2.6")
  implementation("org.javassist:javassist:3.27.0-GA")
  implementation("io.sentry:sentry:3.2.1")
  testImplementation("org.assertj:assertj-core:3.19.0")
  testImplementation("io.mockk:mockk:1.10.5")
}

configurations {
  implementation.configure {
    // sentry brings in a slf4j that breaks when
    // with the platform slf4j
    exclude("org.slf4j")
  }
}

// Configure gradle-intellij-plugin plugin.
// Read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
  version = platformVersion
  type = platformType
  downloadSources = platformDownloadSources.toBoolean()
  updateSinceUntilBuild = true
  alternativeIdePath = idePath

  // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
  setPlugins(
    *platformPlugins.split(',')
      .filter { System.getenv("ENV") == "DEV" }
      .map(String::trim)
      .filter(String::isNotEmpty)
      .toTypedArray()
  )
}

// Configure detekt plugin.
// Read more: https://detekt.github.io/detekt/kotlindsl.html
detekt {
  config = files("./detekt-config.yml")
  buildUponDefaultConfig = true
  autoCorrect = true

  reports {
    html.enabled = false
    xml.enabled = false
    txt.enabled = false
  }
}

tasks {
  // Set the compatibility versions to 1.8
  withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
  }
  listOf("compileKotlin", "compileTestKotlin").forEach {
    getByName<KotlinCompile>(it) {
      kotlinOptions.jvmTarget = "1.8"
    }
  }

  withType<Detekt> {
    jvmTarget = "1.8"
  }

  withType<MarkdownToHtmlTask> {
    sourceDir = file("$projectDir/changelog")
    outputDir = file("$projectDir/build/html")
  }

  patchPluginXml {
    version(pluginVersion)
    sinceBuild(pluginSinceBuild)
    untilBuild(pluginUntilBuild)

    val releaseNotes = file("$projectDir/build/html/RELEASE-NOTES.html")
    if (releaseNotes.exists()) {
      changeNotes(releaseNotes.readText())
    }

    dependsOn("markdownToHtml", "buildThemes")
  }
}
