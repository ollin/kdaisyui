package io.github.ollin.kdaisyui.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.SourceSetContainer

/**
 * `id("io.github.ollin.kdaisyui")`: registers `kdaisyuiClasses`, which writes
 * `build/kdaisyui/kdaisyui-classes.txt` with the classes of the kdaisyui components the main source
 * set's compiled code uses, and `kdaisyuiStylesheet`, which compiles the entry stylesheet configured in
 * `kdaisyui { stylesheet { … } }` in a Node container. Needs the `java` plugin, which Kotlin's JVM
 * plugin applies.
 */
class KdaisyuiPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val stylesheet = project.extensions.create("kdaisyui", KdaisyuiExtension::class.java).stylesheet
        stylesheet.apply {
            sources.convention(project.layout.projectDirectory.dir("src/main/kotlin"))
            output.convention(project.layout.buildDirectory.file("kdaisyui/app.css"))
            nodeImage.convention("node:${KdaisyuiVersions.node}-slim")
            npmRegistry.convention("https://registry.npmjs.org/")
            minify.convention(true)
        }
        project.plugins.withType(JavaPlugin::class.java) {
            val main = project.extensions.getByType(SourceSetContainer::class.java).getByName("main")
            // One directory for both: the stylesheet task copies it in whole, so the entry names the list by one path.
            val kdaisyuiDirectory = project.layout.buildDirectory.dir("kdaisyui")
            val classesTask = project.tasks.register("kdaisyuiClasses", KdaisyuiClasses::class.java) {
                description = "Writes the classes of the kdaisyui components this build uses, for Tailwind's @source."
                classes.from(main.output.classesDirs)
                // The dependencies, not main.runtimeClasspath: that holds the project's own resources,
                // and a build whose stylesheet is a resource would wait on itself.
                runtimeClasspath.from(project.configurations.getByName(main.runtimeClasspathConfigurationName))
                output.set(kdaisyuiDirectory.map { it.file("kdaisyui-classes.txt") })
            }
            project.tasks.register("kdaisyuiStylesheet", KdaisyuiStylesheet::class.java) {
                description = "Compiles the stylesheet with the plugin's Tailwind and DaisyUI, in a Node container."
                entry.set(stylesheet.entry)
                sources.from(stylesheet.sources)
                classList.set(kdaisyuiDirectory)
                dependsOn(classesTask)
                nodeImage.set(stylesheet.nodeImage)
                tailwind.set(KdaisyuiVersions.tailwind)
                daisyui.set(KdaisyuiVersions.daisyui)
                npmRegistry.set(stylesheet.npmRegistry)
                npmUser.set(stylesheet.npmUser)
                npmPassword.set(stylesheet.npmPassword)
                minify.set(stylesheet.minify)
                output.set(stylesheet.output)
            }
        }
    }
}
