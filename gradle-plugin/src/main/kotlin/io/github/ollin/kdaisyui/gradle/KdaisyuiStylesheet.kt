package io.github.ollin.kdaisyui.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import java.io.File

/** Compiles the entry stylesheet with the plugin's Tailwind and DaisyUI, in a Node container. */
@CacheableTask
abstract class KdaisyuiStylesheet : DefaultTask() {

    @get:InputFile
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val entry: RegularFileProperty

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val sources: ConfigurableFileCollection

    /** Where `kdaisyuiClasses` writes its class list; copied in whole, so the entry names it by one path. */
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val classList: DirectoryProperty

    @get:Input abstract val nodeImage: Property<String>
    @get:Input abstract val tailwind: Property<String>
    @get:Input abstract val daisyui: Property<String>
    @get:Input abstract val npmRegistry: Property<String>

    // A secret is no cache key: the registry decides the packages, the credential only opens it.
    @get:Internal abstract val npmUser: Property<String>
    @get:Internal abstract val npmPassword: Property<String>

    @get:Input abstract val minify: Property<Boolean>

    @get:OutputFile
    abstract val output: RegularFileProperty

    /** What the container gets. Half a credential fails here, before any container starts. */
    fun plan(): StylesheetRun = StylesheetRun(
        image = nodeImage.get(),
        npmrc = NpmConfig.npmrc(npmRegistry.get(), npmUser.orNull, npmPassword.orNull),
        command = StylesheetCompile.command(tailwind.get(), daisyui.get(), minify.get()),
        copies = mapOf(entry.get().asFile to "/deps/project/css/app.css", classList.get().asFile to "/deps/project/kdaisyui") +
            StylesheetCompile.mountPoints(sources.files.toList()),
    )

    @TaskAction
    fun compile() {
        NodeContainerRun.compile(plan(), output.get().asFile)
    }
}

/** One compile: the image, the `.npmrc`, the command, and each host file or directory with its place in the container. */
data class StylesheetRun(val image: String, val npmrc: String, val command: List<String>, val copies: Map<File, String>)
