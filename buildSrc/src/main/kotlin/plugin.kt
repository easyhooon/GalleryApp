@file:Suppress("NOTHING_TO_INLINE", "ObjectPropertyName")

import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

inline fun PluginDependenciesSpec.gallery(pluginId: String): PluginDependencySpec =
    id("gallery.plugin.$pluginId")

inline fun PluginDependenciesSpec.android(pluginId: String): PluginDependencySpec =
    id("com.android.$pluginId")
