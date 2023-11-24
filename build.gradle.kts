@file:Suppress("DSL_SCOPE_VIOLATION")

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.kotlin.detekt)
    alias(libs.plugins.kotlin.ktlint)
    alias(libs.plugins.gradle.dependency.handler.extensions)
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.hilt) apply false
    alias(libs.plugins.ksp) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.gradle.kotlin)
    }
}

allprojects {
    val projectPath = rootProject.file(".").absolutePath

    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    apply {
        plugin(rootProject.libs.plugins.kotlin.detekt.get().pluginId)
        plugin(rootProject.libs.plugins.kotlin.ktlint.get().pluginId)
        plugin(rootProject.libs.plugins.gradle.dependency.handler.extensions.get().pluginId)
    }

    afterEvaluate {
        extensions.configure<DetektExtension> {
            parallel = true
            buildUponDefaultConfig = true
            toolVersion = libs.versions.kotlin.detekt.get()
            config.setFrom(files("$rootDir/detekt-config.yml"))
        }

        extensions.configure<KtlintExtension> {
            version.set(rootProject.libs.versions.kotlin.ktlint.source.get())
            android.set(true)
            verbose.set(true)
        }

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-opt-in=kotlin.OptIn",
                    "-opt-in=kotlin.RequiresOptIn",
                )
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$projectPath/report/compose-metrics",
                )
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$projectPath/report/compose-reports",
                )
            }
        }
    }
}

tasks.register("cleanAll", type = Delete::class) {
    allprojects.map(Project::getBuildDir).forEach(::delete)
}

tasks.register("clean", type = Delete::class) {
    rootProject.buildDir.delete()
}

tasks.register("bundleRelease", type = Exec::class) {
    commandLine(project.rootDir.resolve("gradlew"), "bundle")
    workingDir = project.rootDir
}

tasks.register("release") {
    dependsOn(tasks["clean"])
    dependsOn(tasks["bundleRelease"])
    mustRunAfter(tasks["clean"])
}
