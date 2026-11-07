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

    /** Where the artifact's `kdaisyui.css` goes, the rules its markup needs beyond DaisyUI's. */
    @get:OutputFile
    abstract val stylesheet: RegularFileProperty

    @TaskAction
    fun write() {
        val found = classes.asFileTree.matching { include("**/*.class") }.flatMap { componentClassesNamedIn(it.readBytes()) }.toSet()
        val selection = ComponentMap(resource(COMPONENT_MAP)).select(found)
        val reason = selection.fullListBecause
        if (reason != null) logger.warn("kdaisyui: writing every class, because $reason")
        stylesheet.get().asFile.writeText(resource(STYLESHEET))
        output.get().asFile.writeText(HEADER + fullListLine(reason) + selection.classes.joinToString("\n", postfix = "\n"))
    }

    /** Whoever opens the file sees why it holds every class, not only whoever read the build's log. */
    private fun fullListLine(reason: String?): String = if (reason == null) "" else "# Every class, because $reason.\n"

    private fun resource(name: String): String = checkNotNull(runtimeClasspath.firstNotNullOfOrNull { resourceIn(it, name) }) {
        "$name is on no runtime classpath entry: does the build depend on io.github.ollin.kdaisyui:kdaisyui?"
    }

    private companion object {
        const val HEADER = "# The classes of the kdaisyui components this build uses, written by the kdaisyui Gradle plugin.\n" +
            "# Point Tailwind's @source at this file. Generated - do not edit.\n"
    }
}
