package io.github.ollin.kdaisyui.components

import java.lang.reflect.Method
import java.lang.reflect.Modifier
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Every rendering function takes exactly one lambda, its last parameter, so a trailing lambda
 * always binds to it.
 *
 * Until 0.9.0 each function took two lambdas on the same element, `attrs` and `content`, and the
 * trailing one silently replaced a function's `text`. Hand-written on purpose: a generated
 * assertion would be regenerated with the generator it pins and could never go red.
 *
 * Read from the JVM signatures, where a Kotlin lambda parameter is a `Function1`. The class
 * siblings (`…Classes`) render nothing and take no lambda at all.
 */
class OneBlockTest {

    private val componentClasses: List<Class<*>> =
        checkNotNull(javaClass.classLoader.getResourceAsStream("kdaisyui-components.txt")) { "kdaisyui-components.txt is not on the classpath" }
            .bufferedReader().readLines()
            .filterNot { line -> line.startsWith("#") || line.isBlank() }
            .flatMap { line -> line.split("\t")[1].split(" ") }
            .filter { name -> name.endsWith("Kt") }
            .distinct()
            .map { name -> Class.forName("io.github.ollin.kdaisyui.components.$name") }

    private fun Method.isRenderingFunction() =
        Modifier.isPublic(modifiers) && name.startsWith("daisy") && !name.endsWith("Classes") && !name.contains('$')

    private val renderingFunctions: List<Method> =
        (componentClasses + JoinScope::class.java).flatMap { it.declaredMethods.filter { method -> method.isRenderingFunction() } }

    private fun Method.lambdaCount() = parameterTypes.count { it == Function1::class.java }

    @Test
    fun `every rendering function takes exactly one lambda`() {
        assertEquals(emptyList(), renderingFunctions.filter { it.lambdaCount() != 1 }.map { "${it.declaringClass.simpleName}.${it.name}" })
    }

    @Test
    fun `the lambda is the last parameter, so a trailing lambda binds to it`() {
        assertEquals(emptyList(), renderingFunctions.filter { it.parameterTypes.last() != Function1::class.java }.map { "${it.declaringClass.simpleName}.${it.name}" })
    }

    @Test
    fun `the check reads every component`() {
        assertTrue(renderingFunctions.size > 130, "found ${renderingFunctions.size} rendering functions")
    }
}
