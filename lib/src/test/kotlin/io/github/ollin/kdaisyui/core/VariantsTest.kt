package io.github.ollin.kdaisyui.core

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Stands in for a generated component enum.
 *
 * Declared here rather than testing against a real one on purpose: this file pins what the
 * variant API does, and a generated enum would make it also depend on what the generator
 * currently emits. The shape is identical — an enum implementing [ClassValues] at its own type,
 * with `className` internal.
 */
private enum class SampleSize(internal val className: String) : ClassValues<SampleSize> {
    Small("btn-sm"),
    Large("btn-lg"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

class VariantsTest {

    @Test
    fun enumEntryIsASingleUnprefixedClass() {
        assertEquals(listOf("btn-lg"), SampleSize.Large.classNames)
    }

    @Test
    fun atPrefixesTheClassWithTheVariant() {
        assertEquals(listOf("lg:btn-lg"), at(Breakpoint.Lg, SampleSize.Large).classNames)
    }

    @Test
    fun andKeepsBothApplicationsInWrittenOrder() {
        val responsive = SampleSize.Small and at(Breakpoint.Lg, SampleSize.Large)

        assertEquals(listOf("btn-sm", "lg:btn-lg"), responsive.classNames)
    }

    /**
     * The pattern DaisyUI's own button page documents as `btn-xs sm:btn-sm md:btn-md lg:btn-lg
     * xl:btn-xl` — five breakpoints on one class group. An earlier draft of this change's spec
     * said applying a class at two breakpoints must NOT compile; DaisyUI's documentation refutes
     * that, and this test is what holds the corrected reading in place.
     */
    @Test
    fun aGroupCanBeAnsweredAtSeveralBreakpointsAtOnce() {
        val responsive = SampleSize.Small
            .and(at(Breakpoint.Md, SampleSize.Large))
            .and(at(Breakpoint.Xl, SampleSize.Small))

        assertEquals(listOf("btn-sm", "md:btn-lg", "xl:btn-sm"), responsive.classNames)
    }

    @Test
    fun atPrefixesEveryClassOfACombination() {
        val both = at(Breakpoint.MaxSm, SampleSize.Small and SampleSize.Large)

        assertEquals(listOf("max-sm:btn-sm", "max-sm:btn-lg"), both.classNames)
    }

    @Test
    fun everyBreakpointRendersTailwindsOwnPrefix() {
        assertEquals(
            listOf("sm", "md", "lg", "xl", "2xl", "max-sm", "max-md", "max-lg", "max-xl", "max-2xl"),
            Breakpoint.entries.map { it.prefix },
        )
    }

    @Test
    fun everyStateRendersItsOwnPrefix() {
        assertEquals(listOf("dark", "hover", "focus"), State.entries.map { it.prefix })
    }

    @Test
    fun daisyUiOwnVariantsRenderWithoutATrailingColon() {
        assertEquals(
            listOf("is-drawer-open", "is-drawer-close"),
            DrawerVariant.entries.map { it.prefix },
        )
    }

    @Test
    fun aStateAppliesTheSameWayABreakpointDoes() {
        assertEquals(listOf("dark:btn-lg"), at(State.Dark, SampleSize.Large).classNames)
    }
}
