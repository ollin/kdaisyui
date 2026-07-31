package io.github.ollin.kdaisyui.codegen

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue

class FrontmatterTest {

    @Test
    fun `reads the base class from the component category`() {
        assertEquals("btn", parseButton().baseClass)
    }

    @Test
    fun `reads title and description`() {
        val component = parseButton()

        assertEquals("Button", component.name)
        assertEquals("Buttons allow the user to take actions.", component.description)
    }

    @Test
    fun `an exclusive category becomes a choice`() {
        val color = parseButton().choices.single { it.category == "color" }

        assertEquals(listOf("btn-primary", "btn-error"), color.options.map { it.cssClass })
    }

    @Test
    fun `a non-exclusive category becomes flags`() {
        val behavior = parseButton().flags.single { it.category == "behavior" }

        assertEquals(listOf("btn-active"), behavior.options.map { it.cssClass })
    }

    @Test
    fun `structural categories are not axes`() {
        val categories = parseButton().axes.map { it.category }

        assertTrue(STRUCTURAL_CATEGORIES.none { it in categories }, "got $categories")
    }

    @Test
    fun `carries the per-option description`() {
        val color = parseButton().choices.single { it.category == "color" }

        assertEquals("primary color", color.options.first().description)
    }

    @Test
    fun `keeps DaisyUI's default marker`() {
        val placement = Frontmatter.parse(TOOLTIP_PAGE, "DIV").choices.single()

        assertEquals("tooltip-top", placement.default?.cssClass)
    }

    @Test
    fun `has no default when DaisyUI marks none`() {
        assertNull(parseButton().choices.single { it.category == "color" }.default)
    }

    @Test
    fun `rejects a page without a frontmatter fence`() {
        assertFailsWith<IllegalStateException> { Frontmatter.parse("# just a heading", "DIV") }
    }

    @Test
    fun `rejects a page without a component class`() {
        val page = """
            ---
            title: Nothing
            classnames:
              color:
              - class: x-primary
            ---
        """.trimIndent()

        assertFailsWith<IllegalStateException> { Frontmatter.parse(page, "DIV") }
    }

    private fun parseButton() = Frontmatter.parse(BUTTON_PAGE, "BUTTON")

    private companion object {
        val BUTTON_PAGE = """
            ---
            title: Button
            desc: Buttons allow the user to take actions.
            classnames:
              component:
              - class: 'btn'
                desc: Button
              color:
              - class: btn-primary
                desc: primary color
              - class: btn-error
                desc: error color
              style:
              - class: btn-outline
                desc: outline style
              behavior:
              - class: btn-active
                desc: looks active
            ---

            Body text that must be ignored.
        """.trimIndent()

        val TOOLTIP_PAGE = """
            ---
            title: Tooltip
            classnames:
              component:
              - class: tooltip
              placement:
              - class: tooltip-top
                desc: Put tooltip on top
                default: true
              - class: tooltip-bottom
                desc: Put tooltip on bottom
            ---
        """.trimIndent()
    }
}
