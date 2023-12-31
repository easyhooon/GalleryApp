@file:Suppress("DSL_SCOPE_VIOLATION", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
    `kotlin-dsl`
    kotlin("jvm") version libs.versions.kotlin.core.get()
    alias(libs.plugins.gradle.dependency.handler.extensions)
}

gradlePlugin {
    val pluginClasses = listOf(
        "AndroidApplicationPlugin" to "android-application",
        "AndroidLibraryPlugin" to "android-library",
        "AndroidComposePlugin" to "android-compose",
        "AndroidHiltPlugin" to "android-hilt",
        "JvmKotlinPlugin" to "jvm-kotlin",
    )

    plugins {
        pluginClasses.forEach { pluginClass ->
            pluginRegister(pluginClass)
        }
    }
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

sourceSets {
    getByName("main").java.srcDir("src/main/kotlin")
}

dependencies {
    implementations(
        libs.gradle.android,
        libs.gradle.kotlin,
    )
}

// Pair<ClassName, PluginName>
fun NamedDomainObjectContainer<PluginDeclaration>.pluginRegister(data: Pair<String, String>) {
    val (className, pluginName) = data
    register(pluginName) {
        implementationClass = className
        id = "gallery.plugin.$pluginName"
    }
}
