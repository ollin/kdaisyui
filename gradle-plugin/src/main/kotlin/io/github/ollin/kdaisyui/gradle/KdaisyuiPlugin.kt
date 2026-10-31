package io.github.ollin.kdaisyui.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

/** `id("io.github.ollin.kdaisyui")`: narrows the kdaisyui class list to the components a build uses. */
class KdaisyuiPlugin : Plugin<Project> {
    override fun apply(project: Project) = Unit
}
