package io.github.ollin.kdaisyui.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault

/**
 * Warns about strings of the build's compiled code that write DaisyUI classes by hand, which the
 * typed API would write for it and the compiler would then check. It declares no output, so it
 * runs every time the class-list task it finalizes does.
 */
@DisableCachingByDefault(because = "It only logs; a cached result would hide the warning.")
abstract class KdaisyuiRawClasses : DefaultTask() {

    /** The build's compiled classes. */
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val classes: ConfigurableFileCollection

    /** Where the kdaisyui artifact, and with it the component map, is found. */
    @get:Classpath
    abstract val runtimeClasspath: ConfigurableFileCollection

    /** Fails the build with the warning's text instead of logging it. */
    @get:Input
    abstract val failBuild: Property<Boolean>

    init {
        failBuild.convention(false)
    }

    @TaskAction
    fun check() {
        val warning = warning() ?: return
        if (failBuild.get()) throw GradleException(warning)
        logger.warn(warning)
    }

    /** The warning this build deserves, or null; null too for a library without the component map. */
    fun warning(): String? {
        val map = runtimeClasspath.firstNotNullOfOrNull { resourceIn(it, COMPONENT_MAP) }?.let(::ComponentMap)
            ?: return null.also { logger.info("kdaisyui: no $COMPONENT_MAP on the runtime classpath, so raw classes are not checked") }
        val found = classes.files.flatMap { root ->
            root.walk().filter { it.extension == "class" }.flatMap { rawClassListsIn(it.relativeTo(root).invariantSeparatorsPath.removeSuffix(".class"), it.readBytes(), map) }
        }
        return rawClassesWarning(found)
    }
}
