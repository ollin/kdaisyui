package io.github.ollin.kdaisyui.gradle

import java.util.Properties

/** The Tailwind, DaisyUI and Node versions the plugin's build wrote into its jar; the compile installs these. */
object KdaisyuiVersions {
    private val written = Properties().apply { load(KdaisyuiVersions::class.java.getResourceAsStream("/kdaisyui-versions.properties")) }

    val tailwind: String = written.getProperty("tailwind")
    val daisyui: String = written.getProperty("daisyui")
    val node: String = written.getProperty("node")

    /** The plugin's own version, which is the library's it was released with. */
    val kdaisyui: String = written.getProperty("kdaisyui")
}
