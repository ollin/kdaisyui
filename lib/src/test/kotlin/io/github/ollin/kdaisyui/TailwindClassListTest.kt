package io.github.ollin.kdaisyui

import io.github.ollin.kdaisyui.components.ButtonSize
import io.github.ollin.kdaisyui.components.ButtonVariant
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * The Tailwind class list must reach consumers as a classpath resource.
 *
 * This is the only route by which a consuming application's CSS build can learn the class
 * names this library emits. `btn-primary` is assembled at runtime from
 * `ButtonVariant.Primary`, so it appears in no file a Tailwind content scan would read, and
 * the published jar contains compiled classes and no Kotlin sources.
 *
 * A file that silently stops being packaged is exactly the failure this library keeps
 * having: nothing breaks, nothing fails, and a consumer's buttons quietly render unstyled.
 */
class TailwindClassListTest {

    private val classList: String =
        javaClass.classLoader.getResourceAsStream(RESOURCE)?.bufferedReader()?.readText()
            ?: error("$RESOURCE is not on the classpath")

    private val entries: Set<String> =
        classList.lineSequence()
            .map { it.trim() }
            .filter { it.isNotEmpty() && !it.startsWith("#") }
            .toSet()

    @Test
    fun the_resource_is_packaged() {
        assertNotNull(javaClass.classLoader.getResource(RESOURCE))
    }

    @Test
    fun it_carries_the_base_class_of_a_component() {
        assertContains(entries, "btn")
    }

    /** The case that motivates the file: a class no consumer's own sources ever contain. */
    @Test
    fun it_carries_classes_assembled_from_enum_values() {
        assertContains(entries, ButtonVariant.Primary.className)
        assertContains(entries, ButtonSize.Lg.className)
    }

    @Test
    fun it_carries_a_sub_component_part() {
        assertContains(entries, "modal-box")
    }

    @Test
    fun it_explains_itself_to_whoever_opens_it() {
        assertTrue(classList.startsWith("#"), "expected a comment header, got: ${classList.take(40)}")
    }

    @Test
    fun it_is_not_suspiciously_short() {
        assertTrue(entries.size > 400, "expected the full DaisyUI surface, got ${entries.size} classes")
    }

    private companion object {
        const val RESOURCE = "kdaisyui-classes.txt"
    }
}
