package io.github.ollin.kdaisyui.codegen

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class EmitterTest {

    @Test
    fun `an exclusive axis becomes one nullable enum parameter named after the category`() {
        assertContains(emitButton(), "style: ButtonStyle? = null")
    }

    @Test
    fun `mutually exclusive options cannot be combined`() {
        val source = emitButton()

        // The five DaisyUI styles are one parameter, so outline + ghost cannot be expressed.
        assertFalse(source.contains("outline: Boolean"), "outline leaked as a flag")
        assertFalse(source.contains("ghost: Boolean"), "ghost leaked as a flag")
    }

    @Test
    fun `each flag option becomes its own boolean parameter`() {
        val source = emitButton()

        assertContains(source, "active: Boolean = false")
        assertContains(source, "disabled: Boolean = false")
    }

    @Test
    fun `enum constants drop the base class prefix`() {
        assertContains(emitButton(), """Outline("btn-outline")""")
    }

    @Test
    fun `a multi word suffix becomes one PascalCase constant`() {
        val component = button(Axis.Choice("size", listOf(option("btn-extra-large"))))

        assertContains(emit(component), """ExtraLarge("btn-extra-large")""")
    }

    @Test
    fun `the function extends FlowContent and takes the element as lambda receiver`() {
        val source = emitButton()

        assertContains(source, "public fun FlowContent.daisyButton(")
        assertContains(source, "content: (BUTTON.() -> Unit)? = null")
    }

    @Test
    fun `the body always applies the base class`() {
        assertContains(emitButton(), """add("btn")""")
    }

    @Test
    fun `a choice contributes its css class only when set`() {
        assertContains(emitButton(), "style?.let { add(it.cssClass) }")
    }

    @Test
    fun `a flag contributes its css class only when true`() {
        assertContains(emitButton(), """if (active) add("btn-active")""")
    }

    @Test
    fun `emits choices first, then flags, then the fixed parameters`() {
        assertEquals(
            listOf("style", "active", "disabled", "text", "id", "extraClasses", "content"),
            emittedParameterNames(emitButton()),
        )
    }

    private fun emitButton() = emit(button())

    private fun emit(component: Component) = Emitter.emit(component).toString()

    /** Parameter names of the emitted `daisy…` function, in order. */
    private fun emittedParameterNames(source: String): List<String> =
        source.substringAfter("public fun FlowContent.daisy")
            .substringAfter("(")
            .substringBefore("\n) {")
            .lines()
            .filter { it.isNotBlank() }
            .map { it.trim().substringBefore(":") }

    private fun button(vararg extraAxes: Axis) = Component(
        name = "Button",
        baseClass = "btn",
        description = "Buttons.",
        element = "BUTTON",
        axes = listOf(
            Axis.Choice("style", listOf(option("btn-outline"), option("btn-ghost"))),
            Axis.Flags("behavior", listOf(option("btn-active"), option("btn-disabled"))),
        ) + extraAxes,
    )

    private fun option(cssClass: String) = ClassOption(cssClass, "$cssClass description")
}
