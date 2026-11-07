package io.github.ollin.kdaisyui.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.SourceSetContainer

/**
 * `id("io.github.ollin.kdaisyui")`: registers `kdaisyuiClasses`, which writes
 * `build/kdaisyui/kdaisyui-classes.txt` with the classes of the kdaisyui components the main source
 * set's compiled code uses, and `build/kdaisyui/kdaisyui.css` beside it. Needs the `java` plugin, which Kotlin's JVM plugin applies.
 */
class KdaisyuiPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.withType(JavaPlugin::class.java) {
            val main = project.extensions.getByType(SourceSetContainer::class.java).getByName("main")
            project.tasks.register("kdaisyuiClasses", KdaisyuiClasses::class.java) {
                description = "Writes the classes of the kdaisyui components this build uses, for Tailwind's @source."
                classes.from(main.output.classesDirs)
                // The dependencies, not main.runtimeClasspath: that holds the project's own resources,
                // and a build whose stylesheet is a resource would wait on itself.
                runtimeClasspath.from(project.configurations.getByName(main.runtimeClasspathConfigurationName))
                output.set(project.layout.buildDirectory.file("kdaisyui/kdaisyui-classes.txt"))
                stylesheet.set(project.layout.buildDirectory.file("kdaisyui/kdaisyui.css"))
            }
        }
    }
}
