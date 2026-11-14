package io.github.ollin.kdaisyui.gradle

import org.gradle.api.Action
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested

/** `kdaisyui { stylesheet { … }; rawClasses { … } }`. */
abstract class KdaisyuiExtension {
    @get:Nested
    abstract val stylesheet: StylesheetSettings

    fun stylesheet(configure: Action<StylesheetSettings>) = configure.execute(stylesheet)

    @get:Nested
    abstract val rawClasses: RawClassesSettings

    fun rawClasses(configure: Action<RawClassesSettings>) = configure.execute(rawClasses)
}

/** What the check for DaisyUI classes written as strings does with what it finds. */
abstract class RawClassesSettings {
    /** Fail the build instead of warning; `false` by default. */
    abstract val failBuild: Property<Boolean>
}

/**
 * What the stylesheet compile reads. Everything but [entry] has a default: the main Kotlin sources,
 * `build/kdaisyui/app.css`, the Node image of the plugin's pinned version, npm's public registry,
 * no credential, and a minified output.
 */
abstract class StylesheetSettings {
    /** The entry stylesheet, with Tailwind's `@import` and DaisyUI's `@plugin`. */
    abstract val entry: RegularFileProperty

    /** The directories Tailwind scans for class names written in the build's own code. */
    abstract val sources: ConfigurableFileCollection

    abstract val output: RegularFileProperty
    abstract val nodeImage: Property<String>

    /** The npm registry the packages come from: a mirror, where the build has no internet. */
    abstract val npmRegistry: Property<String>
    abstract val npmUser: Property<String>
    abstract val npmPassword: Property<String>
    abstract val minify: Property<Boolean>
}
