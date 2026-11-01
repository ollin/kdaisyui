package io.github.ollin.kdaisyui

import io.github.ollin.kdaisyui.components.ButtonColor
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * `kdaisyui-components.txt` must reach the Gradle plugin as a classpath resource of this jar, so the
 * classes it narrows to always match the library version a build uses.
 */
class ComponentMapTest {

    private val button: List<String> =
        checkNotNull(javaClass.classLoader.getResourceAsStream("kdaisyui-components.txt")) { "kdaisyui-components.txt is not on the classpath" }
            .bufferedReader().readLines().first { it.startsWith("button\t") }.split("\t")

    @Test
    fun aComponentNamesTheJvmClassesItsUseLeavesAndTheClassesItEmits() {
        assertEquals(listOf(true, true, true), listOf("ButtonKt" in button[1].split(" "), "ButtonColor" in button[1].split(" "), ButtonColor.Primary.className in button[2].split(" ")))
    }
}
