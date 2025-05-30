plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "2.1.21"
  id("org.jetbrains.intellij.platform") version "2.6.0"
}

group = "com.github.ivw"
version = "1.0"

kotlin {
  jvmToolchain(21)
}

repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
  intellijPlatform {
    create("IC", "2024.2.5")
    testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

    // Add necessary plugin dependencies for compilation here, example:
    // bundledPlugin("com.intellij.java")
  }
}

intellijPlatform {
  pluginConfiguration {
    ideaVersion {
      sinceBuild = "242"
    }

    changeNotes = """
      Initial version
    """.trimIndent()
  }

  pluginVerification {
    ides {
      recommended()
    }
  }
}
