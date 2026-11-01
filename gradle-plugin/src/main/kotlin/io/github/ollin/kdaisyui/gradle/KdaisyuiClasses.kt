package io.github.ollin.kdaisyui.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

/** Writes the classes of the kdaisyui components the [classes] reference, for Tailwind's `@source`. */
@CacheableTask
abstract class KdaisyuiClasses : DefaultTask() {

    /** The build's compiled classes. */
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val classes: ConfigurableFileCollection

    /** Where the kdaisyui artifact, and with it the component map, is found. */
    @get:Classpath
    abstract val runtimeClasspath: ConfigurableFileCollection

    @get:OutputFile
    abstract val output: RegularFileProperty

    @TaskAction
    fun write() {
        val found = classes.asFileTree.matching { include("**/*.class") }.flatMap { componentClassesNamedIn(it.readBytes()) }.toSet()
        val selection = ComponentMap(componentMap()).select(found)
        if (selection.fullListBecause != null) logger.warn("kdaisyui: writing every class, because ${selection.fullListBecause}")
        output.get().asFile.writeText(HEADER + selection.classes.joinToString("\n", postfix = "\n"))
    }

    private fun componentMap(): String = checkNotNull(runtimeClasspath.firstNotNullOfOrNull(::componentMapIn)) {
        "$COMPONENT_MAP is on no runtime classpath entry: does the build depend on io.github.ollin.kdaisyui:kdaisyui?"
    }

    private companion object {
        const val HEADER = "# The classes of the kdaisyui components this build uses, written by the kdaisyui Gradle plugin.\n" +
            "# Point Tailwind's @source at this file. Generated - do not edit.\n"
    }
}
