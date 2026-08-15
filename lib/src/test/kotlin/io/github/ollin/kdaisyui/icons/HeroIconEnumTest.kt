package io.github.ollin.kdaisyui.icons

import kotlin.test.Test
import kotlin.test.assertEquals

class HeroIconEnumTest {

    @Test
    fun heroIconSizeSmHasDimension16AndClassName() {
        assertEquals(16, HeroIconSize.Sm.dimension)
        assertEquals("heroicon-sm", HeroIconSize.Sm.className)
    }

    @Test
    fun heroIconSizeMdHasDimension20AndClassName() {
        assertEquals(20, HeroIconSize.Md.dimension)
        assertEquals("heroicon-md", HeroIconSize.Md.className)
    }

    @Test
    fun heroIconSizeLgHasDimension24AndClassName() {
        assertEquals(24, HeroIconSize.Lg.dimension)
        assertEquals("heroicon-lg", HeroIconSize.Lg.className)
    }

    @Test
    fun heroIconSizeHasExactlyThreeEntries() {
        assertEquals(
            listOf("Sm", "Md", "Lg"),
            HeroIconSize.entries.map { it.name },
        )
    }

    @Test
    fun heroIconVariantOutlineHasClassName() {
        assertEquals("heroicon-outline", HeroIconVariant.Outline.className)
    }

    @Test
    fun heroIconVariantSolidHasClassName() {
        assertEquals("heroicon-solid", HeroIconVariant.Solid.className)
    }

    @Test
    fun heroIconVariantHasExactlyTwoEntries() {
        assertEquals(
            listOf("Outline", "Solid"),
            HeroIconVariant.entries.map { it.name },
        )
    }
}
