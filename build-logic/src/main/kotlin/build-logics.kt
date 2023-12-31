// @formatter:off

@file:Suppress("UnstableApiUsage", "unused")

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import internal.ApplicationConstants
import internal.applyPlugins
import internal.configureAndroid
import internal.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

internal abstract class BuildLogicPlugin(private val block: Project.() -> Unit) : Plugin<Project> {
    final override fun apply(project: Project) {
        with(project, block = block)
    }
}

internal class AndroidApplicationPlugin : BuildLogicPlugin(
    {
        applyPlugins(Plugins.AndroidApplication, Plugins.KotlinAndroid)

        extensions.configure<BaseAppModuleExtension> {
            configureAndroid(this)

            defaultConfig {
                targetSdk = ApplicationConstants.TargetSdk
                versionCode = ApplicationConstants.VersionCode
                versionName = ApplicationConstants.VersionName
            }
        }
    },
)

internal class AndroidLibraryPlugin : BuildLogicPlugin(
    {
        applyPlugins(Plugins.AndroidLibrary, Plugins.KotlinAndroid)

        extensions.configure<LibraryExtension> {
            configureAndroid(this)

            defaultConfig.apply {
                targetSdk = ApplicationConstants.TargetSdk
            }
        }
    },
)

internal class JvmKotlinPlugin : BuildLogicPlugin(
    {
        applyPlugins(Plugins.JavaLibrary, Plugins.KotlinJvm)

        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = ApplicationConstants.JavaVersion
            targetCompatibility = ApplicationConstants.JavaVersion
        }

        extensions.configure<KotlinProjectExtension> {
            jvmToolchain(ApplicationConstants.JavaVersionAsInt)
        }

        extensions.configure<SourceSetContainer> {
            getByName("main").java.srcDir("src/main/kotlin")
            getByName("test").java.srcDir("src/test/kotlin")
        }

        dependencies.add("detektPlugins", libs.findLibrary("detekt-plugin-formatting").get())
    },
)

internal class AndroidComposePlugin : BuildLogicPlugin(
    {
        extensions.configure<com.android.build.gradle.BaseExtension> {
            buildFeatures.compose = true

            composeOptions {
                kotlinCompilerExtensionVersion =
                    libs.findVersion("androidx-compose-compiler").get().toString()
            }
        }
    },
)

internal class AndroidHiltPlugin : BuildLogicPlugin(
    {
        applyPlugins(
            libs.findPlugin("android-hilt").get().get().pluginId,
            Plugins.Ksp,
        )

        dependencies.add("ksp", libs.findLibrary("android-hilt-compile").get())
        dependencies.add("implementation", libs.findLibrary("android-hilt-runtime").get())
    },
)

