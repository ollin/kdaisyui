package io.github.ollin.kdaisyui.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault

const val THEME_MAP = "kdaisyui-themes.txt"

/**
 * Warns when the build's compiled code names a `DaisyTheme` its compiled stylesheet does not carry,
 * which would otherwise render in the default theme without an error anywhere. It declares no
 * output, so it runs every time the stylesheet task it finalizes does.
 */
@DisableCachingByDefault(because = "It only logs; a cached result would hide the warning.")
abstract class KdaisyuiThemes : DefaultTask() {

    /** The build's compiled classes. */
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val classes: ConfigurableFileCollection

    /** The compiled stylesheet. */
    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val stylesheet: RegularFileProperty

    /** Where the kdaisyui artifact, and with it the theme map, is found. */
    @get:Classpath
    abstract val runtimeClasspath: ConfigurableFileCollection

    @TaskAction
    fun check() {
        warning()?.let(logger::warn)
    }

    /** The warning this build deserves, or null; null too for a library older than the theme map. */
    fun warning(): String? {
        val map = runtimeClasspath.firstNotNullOfOrNull { resourceIn(it, THEME_MAP) }
            ?: return null.also { logger.info("kdaisyui: no $THEME_MAP on the runtime classpath, so the themes are not checked") }
        val themes = themeMap(map)
        val named = classes.asFileTree.matching { include("**/*.class") }.flatMap { themesNamedIn(memberReferencesIn(it.readBytes()), themes) }.toSet()
        return themeWarning(missingThemes(named, themesCarriedBy(stylesheet.get().asFile.readText())))
    }
}
