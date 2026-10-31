package io.github.ollin.kdaisyui.gradle

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertTrue

class KdaisyuiPluginTest {

    @Test
    fun thePluginAppliesById() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("io.github.ollin.kdaisyui")
        assertTrue(project.plugins.hasPlugin(KdaisyuiPlugin::class.java))
    }
}
