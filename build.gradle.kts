plugins {
    // id("org.jetbrains.kotlin.android") apply false
    id("com.android.application") apply false
    // id("com.android.library") version "8.12.0" apply false
    kotlin("android") apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.nexus.publish)
    cleanup
    base
}

allprojects {
    val GROUP: String by project
    val VERSION: String by project
    val USE_SNAPSHOT: String? by project
    group = GROUP
    version = if (USE_SNAPSHOT.toBoolean()) "$VERSION-SNAPSHOT" else VERSION
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.12.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    }
}

val detektFormatting = libs.detekt.formatting

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    detekt {
        config.from(rootProject.files("config/detekt/detekt.yml"))
    }

    dependencies {
        detektPlugins(detektFormatting)
    }
}