// GENERATED — DO NOT EDIT
// Exhaustive render coverage for every generated heroIcon* function.
// Regenerate: cd codegen && npm run generate:heroicon-tests

package io.github.ollin.kdaisyui.icons

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertTrue

class HeroIconsGeneratedTest {
    @Test
    fun heroIconAcademicCap_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAcademicCap() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconAcademicCap Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAcademicCap(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconAcademicCap Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAcademicCap(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconAcademicCap Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAcademicCap(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconAcademicCap Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconAdjustmentsHorizontal_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAdjustmentsHorizontal() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconAdjustmentsHorizontal Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAdjustmentsHorizontal(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconAdjustmentsHorizontal Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAdjustmentsHorizontal(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconAdjustmentsHorizontal Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAdjustmentsHorizontal(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconAdjustmentsHorizontal Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconAdjustmentsVertical_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAdjustmentsVertical() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconAdjustmentsVertical Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAdjustmentsVertical(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconAdjustmentsVertical Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAdjustmentsVertical(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconAdjustmentsVertical Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAdjustmentsVertical(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconAdjustmentsVertical Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArchiveBox_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBox() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArchiveBox Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBox(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArchiveBox Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBox(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArchiveBox Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBox(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArchiveBox Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArchiveBoxArrowDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBoxArrowDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArchiveBoxArrowDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBoxArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArchiveBoxArrowDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBoxArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArchiveBoxArrowDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBoxArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArchiveBoxArrowDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArchiveBoxXMark_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBoxXMark() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArchiveBoxXMark Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBoxXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArchiveBoxXMark Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBoxXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArchiveBoxXMark Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArchiveBoxXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArchiveBoxXMark Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowDownCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowDownCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowDownCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowDownCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowDownCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowDownLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowDownLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowDownLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowDownLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowDownLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowDownOnSquare_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownOnSquare() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowDownOnSquare Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownOnSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowDownOnSquare Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownOnSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowDownOnSquare Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownOnSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowDownOnSquare Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowDownOnSquareStack_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownOnSquareStack() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowDownOnSquareStack Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownOnSquareStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowDownOnSquareStack Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownOnSquareStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowDownOnSquareStack Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownOnSquareStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowDownOnSquareStack Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowDownRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowDownRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowDownRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowDownRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowDownRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowDownTray_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownTray() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowDownTray Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownTray(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowDownTray Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownTray(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowDownTray Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowDownTray(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowDownTray Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowLeftCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowLeftCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowLeftCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowLeftCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowLeftCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowLeftEndOnRectangle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftEndOnRectangle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowLeftEndOnRectangle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftEndOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowLeftEndOnRectangle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftEndOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowLeftEndOnRectangle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftEndOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowLeftEndOnRectangle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowLeftOnRectangle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftOnRectangle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowLeftOnRectangle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowLeftOnRectangle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowLeftOnRectangle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowLeftOnRectangle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowLeftStartOnRectangle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftStartOnRectangle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowLeftStartOnRectangle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftStartOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowLeftStartOnRectangle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftStartOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowLeftStartOnRectangle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLeftStartOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowLeftStartOnRectangle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowLongDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowLongDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowLongDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowLongDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowLongDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowLongLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowLongLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowLongLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowLongLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowLongLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowLongRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowLongRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowLongRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowLongRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowLongRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowLongUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowLongUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowLongUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowLongUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowLongUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowLongUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowPath_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowPath() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowPath Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowPath(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowPath Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowPath(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowPath Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowPath(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowPath Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowPathRoundedSquare_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowPathRoundedSquare() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowPathRoundedSquare Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowPathRoundedSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowPathRoundedSquare Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowPathRoundedSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowPathRoundedSquare Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowPathRoundedSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowPathRoundedSquare Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowRightCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowRightCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowRightCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowRightCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowRightCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowRightEndOnRectangle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightEndOnRectangle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowRightEndOnRectangle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightEndOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowRightEndOnRectangle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightEndOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowRightEndOnRectangle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightEndOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowRightEndOnRectangle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowRightOnRectangle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightOnRectangle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowRightOnRectangle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowRightOnRectangle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowRightOnRectangle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowRightOnRectangle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowRightStartOnRectangle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightStartOnRectangle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowRightStartOnRectangle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightStartOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowRightStartOnRectangle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightStartOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowRightStartOnRectangle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowRightStartOnRectangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowRightStartOnRectangle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowSmallDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowSmallDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowSmallDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowSmallDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowSmallDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowSmallLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowSmallLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowSmallLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowSmallLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowSmallLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowSmallRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowSmallRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowSmallRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowSmallRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowSmallRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowSmallUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowSmallUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowSmallUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowSmallUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowSmallUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowSmallUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTopRightOnSquare_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTopRightOnSquare() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTopRightOnSquare Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTopRightOnSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTopRightOnSquare Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTopRightOnSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTopRightOnSquare Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTopRightOnSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTopRightOnSquare Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTrendingDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTrendingDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTrendingDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTrendingDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTrendingDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTrendingDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTrendingDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTrendingDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTrendingDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTrendingUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTrendingUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTrendingUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTrendingUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTrendingUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTrendingUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTrendingUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTrendingUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTrendingUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTurnDownLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnDownLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTurnDownLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnDownLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTurnDownLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnDownLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTurnDownLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnDownLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTurnDownLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTurnDownRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnDownRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTurnDownRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnDownRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTurnDownRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnDownRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTurnDownRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnDownRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTurnDownRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTurnLeftDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnLeftDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTurnLeftDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnLeftDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTurnLeftDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnLeftDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTurnLeftDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnLeftDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTurnLeftDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTurnLeftUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnLeftUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTurnLeftUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnLeftUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTurnLeftUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnLeftUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTurnLeftUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnLeftUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTurnLeftUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTurnRightDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnRightDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTurnRightDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnRightDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTurnRightDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnRightDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTurnRightDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnRightDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTurnRightDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTurnRightUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnRightUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTurnRightUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnRightUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTurnRightUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnRightUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTurnRightUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnRightUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTurnRightUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTurnUpLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnUpLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTurnUpLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnUpLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTurnUpLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnUpLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTurnUpLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnUpLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTurnUpLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowTurnUpRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnUpRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowTurnUpRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnUpRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowTurnUpRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnUpRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowTurnUpRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowTurnUpRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowTurnUpRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUpCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUpCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUpCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUpCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUpCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUpLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUpLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUpLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUpLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUpLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUpOnSquare_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpOnSquare() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUpOnSquare Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpOnSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUpOnSquare Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpOnSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUpOnSquare Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpOnSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUpOnSquare Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUpOnSquareStack_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpOnSquareStack() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUpOnSquareStack Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpOnSquareStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUpOnSquareStack Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpOnSquareStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUpOnSquareStack Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpOnSquareStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUpOnSquareStack Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUpRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUpRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUpRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUpRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUpRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUpTray_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpTray() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUpTray Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpTray(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUpTray Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpTray(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUpTray Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUpTray(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUpTray Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUturnDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUturnDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUturnDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUturnDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUturnDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUturnLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUturnLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUturnLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUturnLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUturnLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUturnRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUturnRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUturnRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUturnRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUturnRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowUturnUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowUturnUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowUturnUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowUturnUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowUturnUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowUturnUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowsPointingIn_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsPointingIn() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowsPointingIn Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsPointingIn(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowsPointingIn Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsPointingIn(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowsPointingIn Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsPointingIn(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowsPointingIn Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowsPointingOut_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsPointingOut() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowsPointingOut Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsPointingOut(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowsPointingOut Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsPointingOut(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowsPointingOut Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsPointingOut(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowsPointingOut Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowsRightLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsRightLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowsRightLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsRightLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowsRightLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsRightLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowsRightLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsRightLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowsRightLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconArrowsUpDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsUpDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconArrowsUpDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsUpDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconArrowsUpDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsUpDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconArrowsUpDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconArrowsUpDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconArrowsUpDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconAtSymbol_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAtSymbol() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconAtSymbol Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAtSymbol(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconAtSymbol Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAtSymbol(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconAtSymbol Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconAtSymbol(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconAtSymbol Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBackspace_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBackspace() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBackspace Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBackspace(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBackspace Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBackspace(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBackspace Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBackspace(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBackspace Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBackward_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBackward() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBackward Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBackward(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBackward Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBackward(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBackward Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBackward(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBackward Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBanknotes_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBanknotes() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBanknotes Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBanknotes(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBanknotes Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBanknotes(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBanknotes Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBanknotes(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBanknotes Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBars2_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars2() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBars2 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars2(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBars2 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars2(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBars2 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars2(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBars2 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBars3_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBars3 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBars3 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBars3 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBars3 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBars3BottomLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3BottomLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBars3BottomLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3BottomLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBars3BottomLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3BottomLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBars3BottomLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3BottomLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBars3BottomLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBars3BottomRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3BottomRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBars3BottomRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3BottomRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBars3BottomRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3BottomRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBars3BottomRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3BottomRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBars3BottomRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBars3CenterLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3CenterLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBars3CenterLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3CenterLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBars3CenterLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3CenterLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBars3CenterLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars3CenterLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBars3CenterLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBars4_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars4() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBars4 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars4(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBars4 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars4(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBars4 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBars4(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBars4 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBarsArrowDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBarsArrowDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBarsArrowDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBarsArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBarsArrowDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBarsArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBarsArrowDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBarsArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBarsArrowDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBarsArrowUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBarsArrowUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBarsArrowUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBarsArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBarsArrowUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBarsArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBarsArrowUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBarsArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBarsArrowUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBattery0_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery0() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBattery0 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery0(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBattery0 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery0(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBattery0 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery0(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBattery0 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBattery100_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery100() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBattery100 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery100(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBattery100 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery100(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBattery100 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery100(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBattery100 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBattery50_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery50() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBattery50 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery50(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBattery50 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery50(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBattery50 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBattery50(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBattery50 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBeaker_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBeaker() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBeaker Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBeaker(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBeaker Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBeaker(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBeaker Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBeaker(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBeaker Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBell_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBell() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBell Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBell(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBell Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBell(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBell Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBell(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBell Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBellAlert_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellAlert() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBellAlert Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellAlert(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBellAlert Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellAlert(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBellAlert Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellAlert(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBellAlert Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBellSlash_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellSlash() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBellSlash Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBellSlash Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBellSlash Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBellSlash Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBellSnooze_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellSnooze() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBellSnooze Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellSnooze(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBellSnooze Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellSnooze(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBellSnooze Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBellSnooze(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBellSnooze Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBold_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBold() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBold Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBold(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBold Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBold(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBold Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBold(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBold Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBolt_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBolt() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBolt Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBolt(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBolt Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBolt(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBolt Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBolt(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBolt Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBoltSlash_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBoltSlash() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBoltSlash Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBoltSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBoltSlash Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBoltSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBoltSlash Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBoltSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBoltSlash Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBookOpen_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookOpen() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBookOpen Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBookOpen Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBookOpen Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBookOpen Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBookmark_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmark() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBookmark Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmark(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBookmark Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmark(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBookmark Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmark(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBookmark Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBookmarkSlash_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmarkSlash() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBookmarkSlash Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmarkSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBookmarkSlash Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmarkSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBookmarkSlash Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmarkSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBookmarkSlash Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBookmarkSquare_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmarkSquare() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBookmarkSquare Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmarkSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBookmarkSquare Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmarkSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBookmarkSquare Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBookmarkSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBookmarkSquare Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBriefcase_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBriefcase() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBriefcase Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBriefcase(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBriefcase Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBriefcase(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBriefcase Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBriefcase(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBriefcase Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBugAnt_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBugAnt() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBugAnt Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBugAnt(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBugAnt Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBugAnt(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBugAnt Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBugAnt(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBugAnt Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBuildingLibrary_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingLibrary() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBuildingLibrary Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingLibrary(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBuildingLibrary Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingLibrary(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBuildingLibrary Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingLibrary(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBuildingLibrary Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBuildingOffice_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingOffice() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBuildingOffice Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingOffice(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBuildingOffice Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingOffice(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBuildingOffice Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingOffice(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBuildingOffice Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBuildingOffice2_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingOffice2() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBuildingOffice2 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingOffice2(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBuildingOffice2 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingOffice2(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBuildingOffice2 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingOffice2(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBuildingOffice2 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconBuildingStorefront_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingStorefront() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconBuildingStorefront Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingStorefront(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconBuildingStorefront Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingStorefront(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconBuildingStorefront Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconBuildingStorefront(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconBuildingStorefront Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCake_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCake() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCake Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCake(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCake Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCake(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCake Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCake(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCake Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCalculator_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalculator() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCalculator Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalculator(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCalculator Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalculator(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCalculator Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalculator(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCalculator Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCalendar_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendar() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCalendar Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendar(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCalendar Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendar(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCalendar Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendar(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCalendar Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCalendarDateRange_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendarDateRange() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCalendarDateRange Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendarDateRange(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCalendarDateRange Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendarDateRange(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCalendarDateRange Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendarDateRange(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCalendarDateRange Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCalendarDays_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendarDays() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCalendarDays Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendarDays(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCalendarDays Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendarDays(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCalendarDays Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCalendarDays(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCalendarDays Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCamera_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCamera() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCamera Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCamera(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCamera Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCamera(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCamera Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCamera(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCamera Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChartBar_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartBar() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChartBar Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartBar(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChartBar Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartBar(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChartBar Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartBar(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChartBar Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChartBarSquare_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartBarSquare() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChartBarSquare Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartBarSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChartBarSquare Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartBarSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChartBarSquare Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartBarSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChartBarSquare Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChartPie_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartPie() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChartPie Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartPie(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChartPie Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartPie(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChartPie Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChartPie(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChartPie Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChatBubbleBottomCenter_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleBottomCenter() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChatBubbleBottomCenter Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleBottomCenter(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChatBubbleBottomCenter Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleBottomCenter(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChatBubbleBottomCenter Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleBottomCenter(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChatBubbleBottomCenter Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChatBubbleBottomCenterText_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleBottomCenterText() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChatBubbleBottomCenterText Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleBottomCenterText(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChatBubbleBottomCenterText Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleBottomCenterText(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChatBubbleBottomCenterText Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleBottomCenterText(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChatBubbleBottomCenterText Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChatBubbleLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChatBubbleLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChatBubbleLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChatBubbleLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChatBubbleLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChatBubbleLeftEllipsis_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeftEllipsis() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChatBubbleLeftEllipsis Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeftEllipsis(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChatBubbleLeftEllipsis Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeftEllipsis(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChatBubbleLeftEllipsis Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeftEllipsis(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChatBubbleLeftEllipsis Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChatBubbleLeftRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeftRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChatBubbleLeftRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeftRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChatBubbleLeftRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeftRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChatBubbleLeftRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleLeftRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChatBubbleLeftRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChatBubbleOvalLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleOvalLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChatBubbleOvalLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleOvalLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChatBubbleOvalLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleOvalLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChatBubbleOvalLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleOvalLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChatBubbleOvalLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChatBubbleOvalLeftEllipsis_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleOvalLeftEllipsis() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChatBubbleOvalLeftEllipsis Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleOvalLeftEllipsis(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChatBubbleOvalLeftEllipsis Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleOvalLeftEllipsis(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChatBubbleOvalLeftEllipsis Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChatBubbleOvalLeftEllipsis(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChatBubbleOvalLeftEllipsis Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCheck_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheck() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCheck Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCheck Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCheck Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCheck Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCheckBadge_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheckBadge() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCheckBadge Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheckBadge(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCheckBadge Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheckBadge(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCheckBadge Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheckBadge(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCheckBadge Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCheckCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheckCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCheckCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheckCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCheckCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheckCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCheckCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCheckCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCheckCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChevronDoubleDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChevronDoubleDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChevronDoubleDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChevronDoubleDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChevronDoubleDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChevronDoubleLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChevronDoubleLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChevronDoubleLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChevronDoubleLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChevronDoubleLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChevronDoubleRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChevronDoubleRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChevronDoubleRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChevronDoubleRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChevronDoubleRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChevronDoubleUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChevronDoubleUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChevronDoubleUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChevronDoubleUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDoubleUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChevronDoubleUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChevronDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChevronDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChevronDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChevronDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChevronDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChevronLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChevronLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChevronLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChevronLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChevronLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChevronRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChevronRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChevronRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChevronRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChevronRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChevronUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChevronUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChevronUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChevronUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChevronUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconChevronUpDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronUpDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconChevronUpDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronUpDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconChevronUpDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronUpDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconChevronUpDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconChevronUpDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconChevronUpDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCircleStack_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCircleStack() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCircleStack Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCircleStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCircleStack Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCircleStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCircleStack Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCircleStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCircleStack Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconClipboard_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboard() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconClipboard Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboard(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconClipboard Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboard(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconClipboard Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboard(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconClipboard Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconClipboardDocument_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocument() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconClipboardDocument Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocument(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconClipboardDocument Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocument(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconClipboardDocument Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocument(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconClipboardDocument Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconClipboardDocumentCheck_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocumentCheck() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconClipboardDocumentCheck Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocumentCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconClipboardDocumentCheck Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocumentCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconClipboardDocumentCheck Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocumentCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconClipboardDocumentCheck Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconClipboardDocumentList_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocumentList() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconClipboardDocumentList Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocumentList(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconClipboardDocumentList Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocumentList(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconClipboardDocumentList Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClipboardDocumentList(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconClipboardDocumentList Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconClock_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClock() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconClock Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClock(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconClock Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClock(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconClock Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconClock(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconClock Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCloud_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloud() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCloud Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloud(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCloud Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloud(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCloud Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloud(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCloud Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCloudArrowDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloudArrowDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCloudArrowDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloudArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCloudArrowDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloudArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCloudArrowDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloudArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCloudArrowDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCloudArrowUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloudArrowUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCloudArrowUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloudArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCloudArrowUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloudArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCloudArrowUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCloudArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCloudArrowUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCodeBracket_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCodeBracket() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCodeBracket Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCodeBracket(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCodeBracket Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCodeBracket(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCodeBracket Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCodeBracket(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCodeBracket Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCodeBracketSquare_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCodeBracketSquare() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCodeBracketSquare Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCodeBracketSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCodeBracketSquare Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCodeBracketSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCodeBracketSquare Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCodeBracketSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCodeBracketSquare Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCog_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCog Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCog Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCog Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCog Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCog6Tooth_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog6Tooth() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCog6Tooth Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog6Tooth(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCog6Tooth Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog6Tooth(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCog6Tooth Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog6Tooth(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCog6Tooth Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCog8Tooth_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog8Tooth() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCog8Tooth Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog8Tooth(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCog8Tooth Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog8Tooth(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCog8Tooth Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCog8Tooth(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCog8Tooth Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCommandLine_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCommandLine() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCommandLine Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCommandLine(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCommandLine Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCommandLine(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCommandLine Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCommandLine(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCommandLine Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconComputerDesktop_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconComputerDesktop() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconComputerDesktop Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconComputerDesktop(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconComputerDesktop Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconComputerDesktop(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconComputerDesktop Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconComputerDesktop(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconComputerDesktop Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCpuChip_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCpuChip() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCpuChip Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCpuChip(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCpuChip Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCpuChip(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCpuChip Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCpuChip(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCpuChip Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCreditCard_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCreditCard() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCreditCard Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCreditCard(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCreditCard Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCreditCard(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCreditCard Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCreditCard(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCreditCard Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCube_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCube() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCube Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCube(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCube Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCube(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCube Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCube(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCube Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCubeTransparent_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCubeTransparent() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCubeTransparent Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCubeTransparent(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCubeTransparent Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCubeTransparent(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCubeTransparent Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCubeTransparent(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCubeTransparent Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCurrencyBangladeshi_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyBangladeshi() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCurrencyBangladeshi Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyBangladeshi(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCurrencyBangladeshi Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyBangladeshi(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCurrencyBangladeshi Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyBangladeshi(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCurrencyBangladeshi Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCurrencyDollar_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyDollar() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCurrencyDollar Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyDollar(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCurrencyDollar Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyDollar(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCurrencyDollar Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyDollar(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCurrencyDollar Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCurrencyEuro_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyEuro() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCurrencyEuro Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyEuro(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCurrencyEuro Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyEuro(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCurrencyEuro Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyEuro(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCurrencyEuro Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCurrencyPound_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyPound() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCurrencyPound Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyPound(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCurrencyPound Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyPound(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCurrencyPound Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyPound(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCurrencyPound Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCurrencyRupee_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyRupee() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCurrencyRupee Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyRupee(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCurrencyRupee Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyRupee(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCurrencyRupee Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyRupee(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCurrencyRupee Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCurrencyYen_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyYen() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCurrencyYen Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyYen(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCurrencyYen Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyYen(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCurrencyYen Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCurrencyYen(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCurrencyYen Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCursorArrowRays_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCursorArrowRays() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCursorArrowRays Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCursorArrowRays(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCursorArrowRays Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCursorArrowRays(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCursorArrowRays Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCursorArrowRays(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCursorArrowRays Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconCursorArrowRipple_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCursorArrowRipple() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconCursorArrowRipple Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCursorArrowRipple(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconCursorArrowRipple Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCursorArrowRipple(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconCursorArrowRipple Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconCursorArrowRipple(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconCursorArrowRipple Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDevicePhoneMobile_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDevicePhoneMobile() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDevicePhoneMobile Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDevicePhoneMobile(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDevicePhoneMobile Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDevicePhoneMobile(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDevicePhoneMobile Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDevicePhoneMobile(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDevicePhoneMobile Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDeviceTablet_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDeviceTablet() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDeviceTablet Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDeviceTablet(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDeviceTablet Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDeviceTablet(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDeviceTablet Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDeviceTablet(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDeviceTablet Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDivide_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDivide() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDivide Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDivide(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDivide Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDivide(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDivide Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDivide(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDivide Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocument_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocument() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocument Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocument(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocument Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocument(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocument Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocument(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocument Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentArrowDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentArrowDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentArrowDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentArrowDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentArrowDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentArrowDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentArrowUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentArrowUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentArrowUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentArrowUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentArrowUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentArrowUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentArrowUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentChartBar_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentChartBar() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentChartBar Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentChartBar(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentChartBar Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentChartBar(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentChartBar Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentChartBar(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentChartBar Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentCheck_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCheck() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentCheck Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentCheck Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentCheck Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentCheck Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentCurrencyBangladeshi_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyBangladeshi() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentCurrencyBangladeshi Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyBangladeshi(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentCurrencyBangladeshi Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyBangladeshi(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentCurrencyBangladeshi Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyBangladeshi(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentCurrencyBangladeshi Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentCurrencyDollar_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyDollar() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentCurrencyDollar Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyDollar(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentCurrencyDollar Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyDollar(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentCurrencyDollar Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyDollar(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentCurrencyDollar Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentCurrencyEuro_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyEuro() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentCurrencyEuro Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyEuro(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentCurrencyEuro Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyEuro(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentCurrencyEuro Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyEuro(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentCurrencyEuro Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentCurrencyPound_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyPound() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentCurrencyPound Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyPound(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentCurrencyPound Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyPound(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentCurrencyPound Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyPound(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentCurrencyPound Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentCurrencyRupee_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyRupee() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentCurrencyRupee Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyRupee(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentCurrencyRupee Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyRupee(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentCurrencyRupee Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyRupee(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentCurrencyRupee Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentCurrencyYen_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyYen() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentCurrencyYen Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyYen(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentCurrencyYen Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyYen(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentCurrencyYen Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentCurrencyYen(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentCurrencyYen Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentDuplicate_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentDuplicate() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentDuplicate Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentDuplicate(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentDuplicate Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentDuplicate(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentDuplicate Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentDuplicate(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentDuplicate Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentMagnifyingGlass_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentMagnifyingGlass() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentMagnifyingGlass Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentMagnifyingGlass(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentMagnifyingGlass Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentMagnifyingGlass(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentMagnifyingGlass Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentMagnifyingGlass(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentMagnifyingGlass Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentMinus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentMinus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentMinus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentMinus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentMinus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentMinus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentPlus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentPlus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentPlus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentPlus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentPlus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentPlus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconDocumentText_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentText() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconDocumentText Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentText(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconDocumentText Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentText(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconDocumentText Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconDocumentText(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconDocumentText Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconEllipsisHorizontal_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisHorizontal() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconEllipsisHorizontal Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisHorizontal(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconEllipsisHorizontal Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisHorizontal(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconEllipsisHorizontal Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisHorizontal(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconEllipsisHorizontal Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconEllipsisHorizontalCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisHorizontalCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconEllipsisHorizontalCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisHorizontalCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconEllipsisHorizontalCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisHorizontalCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconEllipsisHorizontalCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisHorizontalCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconEllipsisHorizontalCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconEllipsisVertical_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisVertical() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconEllipsisVertical Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisVertical(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconEllipsisVertical Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisVertical(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconEllipsisVertical Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEllipsisVertical(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconEllipsisVertical Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconEnvelope_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEnvelope() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconEnvelope Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEnvelope(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconEnvelope Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEnvelope(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconEnvelope Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEnvelope(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconEnvelope Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconEnvelopeOpen_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEnvelopeOpen() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconEnvelopeOpen Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEnvelopeOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconEnvelopeOpen Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEnvelopeOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconEnvelopeOpen Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEnvelopeOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconEnvelopeOpen Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconEquals_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEquals() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconEquals Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEquals(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconEquals Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEquals(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconEquals Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEquals(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconEquals Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconExclamationCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconExclamationCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconExclamationCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconExclamationCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconExclamationCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconExclamationCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconExclamationCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconExclamationCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconExclamationCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconExclamationTriangle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconExclamationTriangle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconExclamationTriangle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconExclamationTriangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconExclamationTriangle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconExclamationTriangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconExclamationTriangle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconExclamationTriangle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconExclamationTriangle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconEye_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEye() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconEye Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEye(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconEye Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEye(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconEye Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEye(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconEye Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconEyeDropper_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEyeDropper() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconEyeDropper Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEyeDropper(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconEyeDropper Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEyeDropper(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconEyeDropper Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEyeDropper(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconEyeDropper Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconEyeSlash_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEyeSlash() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconEyeSlash Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEyeSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconEyeSlash Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEyeSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconEyeSlash Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconEyeSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconEyeSlash Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFaceFrown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFaceFrown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFaceFrown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFaceFrown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFaceFrown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFaceFrown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFaceFrown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFaceFrown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFaceFrown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFaceSmile_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFaceSmile() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFaceSmile Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFaceSmile(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFaceSmile Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFaceSmile(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFaceSmile Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFaceSmile(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFaceSmile Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFilm_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFilm() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFilm Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFilm(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFilm Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFilm(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFilm Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFilm(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFilm Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFingerPrint_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFingerPrint() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFingerPrint Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFingerPrint(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFingerPrint Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFingerPrint(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFingerPrint Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFingerPrint(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFingerPrint Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFire_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFire() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFire Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFire(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFire Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFire(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFire Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFire(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFire Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFlag_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFlag() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFlag Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFlag(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFlag Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFlag(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFlag Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFlag(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFlag Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFolder_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolder() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFolder Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolder(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFolder Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolder(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFolder Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolder(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFolder Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFolderArrowDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderArrowDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFolderArrowDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFolderArrowDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFolderArrowDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFolderArrowDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFolderMinus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderMinus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFolderMinus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFolderMinus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFolderMinus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFolderMinus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFolderOpen_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderOpen() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFolderOpen Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFolderOpen Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFolderOpen Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFolderOpen Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFolderPlus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderPlus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFolderPlus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFolderPlus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFolderPlus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFolderPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFolderPlus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconForward_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconForward() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconForward Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconForward(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconForward Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconForward(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconForward Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconForward(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconForward Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconFunnel_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFunnel() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconFunnel Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFunnel(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconFunnel Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFunnel(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconFunnel Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconFunnel(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconFunnel Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconGif_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGif() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconGif Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGif(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconGif Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGif(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconGif Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGif(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconGif Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconGift_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGift() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconGift Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGift(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconGift Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGift(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconGift Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGift(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconGift Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconGiftTop_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGiftTop() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconGiftTop Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGiftTop(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconGiftTop Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGiftTop(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconGiftTop Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGiftTop(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconGiftTop Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconGlobeAlt_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAlt() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconGlobeAlt Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAlt(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconGlobeAlt Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAlt(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconGlobeAlt Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAlt(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconGlobeAlt Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconGlobeAmericas_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAmericas() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconGlobeAmericas Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAmericas(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconGlobeAmericas Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAmericas(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconGlobeAmericas Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAmericas(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconGlobeAmericas Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconGlobeAsiaAustralia_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAsiaAustralia() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconGlobeAsiaAustralia Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAsiaAustralia(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconGlobeAsiaAustralia Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAsiaAustralia(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconGlobeAsiaAustralia Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeAsiaAustralia(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconGlobeAsiaAustralia Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconGlobeEuropeAfrica_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeEuropeAfrica() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconGlobeEuropeAfrica Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeEuropeAfrica(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconGlobeEuropeAfrica Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeEuropeAfrica(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconGlobeEuropeAfrica Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconGlobeEuropeAfrica(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconGlobeEuropeAfrica Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconH1_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH1() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconH1 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH1(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconH1 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH1(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconH1 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH1(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconH1 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconH2_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH2() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconH2 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH2(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconH2 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH2(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconH2 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH2(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconH2 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconH3_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH3() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconH3 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH3(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconH3 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH3(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconH3 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconH3(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconH3 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconHandRaised_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandRaised() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconHandRaised Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandRaised(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconHandRaised Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandRaised(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconHandRaised Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandRaised(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconHandRaised Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconHandThumbDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandThumbDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconHandThumbDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandThumbDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconHandThumbDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandThumbDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconHandThumbDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandThumbDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconHandThumbDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconHandThumbUp_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandThumbUp() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconHandThumbUp Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandThumbUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconHandThumbUp Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandThumbUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconHandThumbUp Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHandThumbUp(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconHandThumbUp Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconHashtag_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHashtag() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconHashtag Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHashtag(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconHashtag Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHashtag(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconHashtag Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHashtag(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconHashtag Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconHeart_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHeart() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconHeart Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHeart(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconHeart Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHeart(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconHeart Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHeart(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconHeart Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconHome_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHome() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconHome Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHome(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconHome Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHome(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconHome Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHome(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconHome Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconHomeModern_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHomeModern() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconHomeModern Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHomeModern(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconHomeModern Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHomeModern(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconHomeModern Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconHomeModern(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconHomeModern Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconIdentification_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconIdentification() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconIdentification Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconIdentification(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconIdentification Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconIdentification(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconIdentification Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconIdentification(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconIdentification Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconInbox_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInbox() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconInbox Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInbox(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconInbox Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInbox(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconInbox Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInbox(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconInbox Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconInboxArrowDown_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInboxArrowDown() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconInboxArrowDown Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInboxArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconInboxArrowDown Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInboxArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconInboxArrowDown Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInboxArrowDown(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconInboxArrowDown Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconInboxStack_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInboxStack() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconInboxStack Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInboxStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconInboxStack Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInboxStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconInboxStack Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInboxStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconInboxStack Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconInformationCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInformationCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconInformationCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInformationCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconInformationCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInformationCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconInformationCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconInformationCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconInformationCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconItalic_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconItalic() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconItalic Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconItalic(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconItalic Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconItalic(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconItalic Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconItalic(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconItalic Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconKey_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconKey() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconKey Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconKey(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconKey Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconKey(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconKey Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconKey(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconKey Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconLanguage_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLanguage() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconLanguage Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLanguage(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconLanguage Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLanguage(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconLanguage Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLanguage(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconLanguage Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconLifebuoy_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLifebuoy() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconLifebuoy Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLifebuoy(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconLifebuoy Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLifebuoy(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconLifebuoy Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLifebuoy(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconLifebuoy Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconLightBulb_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLightBulb() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconLightBulb Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLightBulb(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconLightBulb Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLightBulb(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconLightBulb Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLightBulb(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconLightBulb Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconLink_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLink() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconLink Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLink(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconLink Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLink(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconLink Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLink(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconLink Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconLinkSlash_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLinkSlash() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconLinkSlash Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLinkSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconLinkSlash Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLinkSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconLinkSlash Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLinkSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconLinkSlash Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconListBullet_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconListBullet() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconListBullet Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconListBullet(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconListBullet Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconListBullet(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconListBullet Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconListBullet(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconListBullet Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconLockClosed_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLockClosed() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconLockClosed Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLockClosed(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconLockClosed Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLockClosed(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconLockClosed Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLockClosed(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconLockClosed Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconLockOpen_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLockOpen() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconLockOpen Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLockOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconLockOpen Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLockOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconLockOpen Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconLockOpen(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconLockOpen Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMagnifyingGlass_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlass() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMagnifyingGlass Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlass(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMagnifyingGlass Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlass(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMagnifyingGlass Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlass(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMagnifyingGlass Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMagnifyingGlassCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMagnifyingGlassCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMagnifyingGlassCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMagnifyingGlassCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMagnifyingGlassCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMagnifyingGlassMinus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassMinus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMagnifyingGlassMinus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMagnifyingGlassMinus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMagnifyingGlassMinus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMagnifyingGlassMinus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMagnifyingGlassPlus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassPlus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMagnifyingGlassPlus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMagnifyingGlassPlus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMagnifyingGlassPlus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMagnifyingGlassPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMagnifyingGlassPlus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMap_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMap() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMap Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMap(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMap Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMap(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMap Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMap(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMap Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMapPin_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMapPin() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMapPin Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMapPin(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMapPin Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMapPin(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMapPin Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMapPin(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMapPin Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMegaphone_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMegaphone() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMegaphone Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMegaphone(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMegaphone Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMegaphone(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMegaphone Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMegaphone(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMegaphone Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMicrophone_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMicrophone() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMicrophone Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMicrophone(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMicrophone Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMicrophone(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMicrophone Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMicrophone(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMicrophone Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMinus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMinus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMinus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMinus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMinus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMinusCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinusCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMinusCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinusCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMinusCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinusCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMinusCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinusCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMinusCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMinusSmall_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinusSmall() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMinusSmall Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinusSmall(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMinusSmall Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinusSmall(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMinusSmall Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMinusSmall(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMinusSmall Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMoon_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMoon() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMoon Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMoon(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMoon Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMoon(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMoon Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMoon(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMoon Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconMusicalNote_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMusicalNote() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconMusicalNote Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMusicalNote(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconMusicalNote Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMusicalNote(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconMusicalNote Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconMusicalNote(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconMusicalNote Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconNewspaper_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNewspaper() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconNewspaper Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNewspaper(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconNewspaper Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNewspaper(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconNewspaper Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNewspaper(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconNewspaper Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconNoSymbol_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNoSymbol() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconNoSymbol Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNoSymbol(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconNoSymbol Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNoSymbol(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconNoSymbol Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNoSymbol(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconNoSymbol Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconNumberedList_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNumberedList() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconNumberedList Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNumberedList(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconNumberedList Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNumberedList(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconNumberedList Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconNumberedList(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconNumberedList Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPaintBrush_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaintBrush() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPaintBrush Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaintBrush(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPaintBrush Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaintBrush(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPaintBrush Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaintBrush(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPaintBrush Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPaperAirplane_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaperAirplane() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPaperAirplane Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaperAirplane(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPaperAirplane Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaperAirplane(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPaperAirplane Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaperAirplane(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPaperAirplane Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPaperClip_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaperClip() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPaperClip Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaperClip(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPaperClip Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaperClip(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPaperClip Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPaperClip(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPaperClip Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPause_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPause() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPause Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPause(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPause Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPause(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPause Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPause(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPause Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPauseCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPauseCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPauseCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPauseCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPauseCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPauseCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPauseCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPauseCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPauseCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPencil_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPencil() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPencil Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPencil(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPencil Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPencil(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPencil Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPencil(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPencil Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPencilSquare_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPencilSquare() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPencilSquare Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPencilSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPencilSquare Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPencilSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPencilSquare Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPencilSquare(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPencilSquare Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPercentBadge_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPercentBadge() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPercentBadge Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPercentBadge(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPercentBadge Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPercentBadge(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPercentBadge Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPercentBadge(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPercentBadge Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPhone_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhone() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPhone Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhone(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPhone Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhone(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPhone Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhone(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPhone Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPhoneArrowDownLeft_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneArrowDownLeft() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPhoneArrowDownLeft Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneArrowDownLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPhoneArrowDownLeft Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneArrowDownLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPhoneArrowDownLeft Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneArrowDownLeft(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPhoneArrowDownLeft Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPhoneArrowUpRight_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneArrowUpRight() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPhoneArrowUpRight Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneArrowUpRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPhoneArrowUpRight Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneArrowUpRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPhoneArrowUpRight Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneArrowUpRight(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPhoneArrowUpRight Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPhoneXMark_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneXMark() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPhoneXMark Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPhoneXMark Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPhoneXMark Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoneXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPhoneXMark Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPhoto_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoto() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPhoto Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoto(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPhoto Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoto(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPhoto Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPhoto(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPhoto Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPlay_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlay() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPlay Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlay(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPlay Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlay(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPlay Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlay(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPlay Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPlayCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlayCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPlayCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlayCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPlayCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlayCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPlayCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlayCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPlayCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPlayPause_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlayPause() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPlayPause Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlayPause(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPlayPause Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlayPause(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPlayPause Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlayPause(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPlayPause Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPlus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPlus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPlus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPlus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPlus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPlusCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlusCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPlusCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlusCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPlusCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlusCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPlusCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlusCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPlusCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPlusSmall_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlusSmall() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPlusSmall Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlusSmall(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPlusSmall Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlusSmall(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPlusSmall Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPlusSmall(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPlusSmall Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPower_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPower() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPower Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPower(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPower Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPower(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPower Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPower(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPower Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPresentationChartBar_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPresentationChartBar() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPresentationChartBar Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPresentationChartBar(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPresentationChartBar Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPresentationChartBar(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPresentationChartBar Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPresentationChartBar(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPresentationChartBar Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPresentationChartLine_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPresentationChartLine() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPresentationChartLine Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPresentationChartLine(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPresentationChartLine Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPresentationChartLine(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPresentationChartLine Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPresentationChartLine(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPresentationChartLine Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPrinter_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPrinter() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPrinter Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPrinter(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPrinter Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPrinter(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPrinter Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPrinter(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPrinter Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconPuzzlePiece_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPuzzlePiece() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconPuzzlePiece Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPuzzlePiece(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconPuzzlePiece Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPuzzlePiece(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconPuzzlePiece Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconPuzzlePiece(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconPuzzlePiece Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconQrCode_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQrCode() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconQrCode Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQrCode(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconQrCode Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQrCode(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconQrCode Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQrCode(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconQrCode Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconQuestionMarkCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQuestionMarkCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconQuestionMarkCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQuestionMarkCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconQuestionMarkCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQuestionMarkCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconQuestionMarkCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQuestionMarkCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconQuestionMarkCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconQueueList_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQueueList() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconQueueList Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQueueList(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconQueueList Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQueueList(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconQueueList Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconQueueList(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconQueueList Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconRadio_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRadio() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconRadio Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRadio(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconRadio Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRadio(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconRadio Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRadio(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconRadio Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconReceiptPercent_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconReceiptPercent() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconReceiptPercent Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconReceiptPercent(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconReceiptPercent Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconReceiptPercent(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconReceiptPercent Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconReceiptPercent(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconReceiptPercent Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconReceiptRefund_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconReceiptRefund() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconReceiptRefund Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconReceiptRefund(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconReceiptRefund Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconReceiptRefund(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconReceiptRefund Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconReceiptRefund(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconReceiptRefund Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconRectangleGroup_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRectangleGroup() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconRectangleGroup Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRectangleGroup(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconRectangleGroup Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRectangleGroup(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconRectangleGroup Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRectangleGroup(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconRectangleGroup Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconRectangleStack_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRectangleStack() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconRectangleStack Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRectangleStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconRectangleStack Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRectangleStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconRectangleStack Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRectangleStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconRectangleStack Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconRocketLaunch_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRocketLaunch() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconRocketLaunch Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRocketLaunch(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconRocketLaunch Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRocketLaunch(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconRocketLaunch Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRocketLaunch(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconRocketLaunch Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconRss_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRss() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconRss Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRss(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconRss Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRss(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconRss Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconRss(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconRss Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconScale_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconScale() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconScale Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconScale(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconScale Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconScale(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconScale Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconScale(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconScale Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconScissors_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconScissors() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconScissors Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconScissors(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconScissors Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconScissors(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconScissors Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconScissors(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconScissors Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconServer_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconServer() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconServer Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconServer(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconServer Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconServer(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconServer Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconServer(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconServer Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconServerStack_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconServerStack() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconServerStack Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconServerStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconServerStack Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconServerStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconServerStack Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconServerStack(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconServerStack Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconShare_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShare() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconShare Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShare(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconShare Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShare(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconShare Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShare(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconShare Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconShieldCheck_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShieldCheck() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconShieldCheck Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShieldCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconShieldCheck Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShieldCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconShieldCheck Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShieldCheck(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconShieldCheck Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconShieldExclamation_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShieldExclamation() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconShieldExclamation Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShieldExclamation(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconShieldExclamation Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShieldExclamation(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconShieldExclamation Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShieldExclamation(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconShieldExclamation Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconShoppingBag_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShoppingBag() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconShoppingBag Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShoppingBag(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconShoppingBag Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShoppingBag(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconShoppingBag Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShoppingBag(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconShoppingBag Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconShoppingCart_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShoppingCart() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconShoppingCart Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShoppingCart(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconShoppingCart Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShoppingCart(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconShoppingCart Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconShoppingCart(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconShoppingCart Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSignal_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSignal() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSignal Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSignal(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSignal Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSignal(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSignal Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSignal(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSignal Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSignalSlash_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSignalSlash() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSignalSlash Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSignalSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSignalSlash Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSignalSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSignalSlash Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSignalSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSignalSlash Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSlash_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSlash() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSlash Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSlash Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSlash Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSlash Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSparkles_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSparkles() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSparkles Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSparkles(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSparkles Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSparkles(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSparkles Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSparkles(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSparkles Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSpeakerWave_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSpeakerWave() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSpeakerWave Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSpeakerWave(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSpeakerWave Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSpeakerWave(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSpeakerWave Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSpeakerWave(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSpeakerWave Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSpeakerXMark_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSpeakerXMark() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSpeakerXMark Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSpeakerXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSpeakerXMark Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSpeakerXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSpeakerXMark Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSpeakerXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSpeakerXMark Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSquare2Stack_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquare2Stack() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSquare2Stack Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquare2Stack(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSquare2Stack Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquare2Stack(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSquare2Stack Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquare2Stack(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSquare2Stack Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSquare3Stack3d_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquare3Stack3d() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSquare3Stack3d Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquare3Stack3d(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSquare3Stack3d Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquare3Stack3d(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSquare3Stack3d Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquare3Stack3d(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSquare3Stack3d Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSquares2x2_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquares2x2() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSquares2x2 Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquares2x2(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSquares2x2 Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquares2x2(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSquares2x2 Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquares2x2(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSquares2x2 Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSquaresPlus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquaresPlus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSquaresPlus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquaresPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSquaresPlus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquaresPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSquaresPlus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSquaresPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSquaresPlus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconStar_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStar() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconStar Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStar(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconStar Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStar(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconStar Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStar(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconStar Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconStop_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStop() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconStop Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStop(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconStop Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStop(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconStop Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStop(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconStop Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconStopCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStopCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconStopCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStopCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconStopCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStopCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconStopCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStopCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconStopCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconStrikethrough_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStrikethrough() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconStrikethrough Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStrikethrough(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconStrikethrough Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStrikethrough(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconStrikethrough Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconStrikethrough(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconStrikethrough Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSun_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSun() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSun Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSun(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSun Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSun(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSun Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSun(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSun Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconSwatch_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSwatch() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconSwatch Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSwatch(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconSwatch Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSwatch(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconSwatch Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconSwatch(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconSwatch Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconTableCells_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTableCells() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconTableCells Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTableCells(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconTableCells Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTableCells(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconTableCells Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTableCells(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconTableCells Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconTag_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTag() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconTag Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTag(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconTag Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTag(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconTag Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTag(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconTag Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconTicket_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTicket() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconTicket Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTicket(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconTicket Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTicket(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconTicket Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTicket(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconTicket Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconTrash_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTrash() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconTrash Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTrash(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconTrash Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTrash(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconTrash Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTrash(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconTrash Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconTrophy_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTrophy() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconTrophy Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTrophy(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconTrophy Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTrophy(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconTrophy Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTrophy(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconTrophy Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconTruck_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTruck() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconTruck Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTruck(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconTruck Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTruck(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconTruck Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTruck(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconTruck Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconTv_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTv() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconTv Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTv(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconTv Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTv(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconTv Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconTv(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconTv Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconUnderline_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUnderline() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconUnderline Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUnderline(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconUnderline Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUnderline(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconUnderline Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUnderline(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconUnderline Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconUser_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUser() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconUser Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUser(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconUser Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUser(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconUser Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUser(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconUser Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconUserCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconUserCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconUserCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconUserCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconUserCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconUserGroup_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserGroup() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconUserGroup Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserGroup(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconUserGroup Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserGroup(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconUserGroup Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserGroup(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconUserGroup Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconUserMinus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserMinus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconUserMinus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconUserMinus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconUserMinus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserMinus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconUserMinus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconUserPlus_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserPlus() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconUserPlus Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconUserPlus Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconUserPlus Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUserPlus(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconUserPlus Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconUsers_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUsers() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconUsers Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUsers(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconUsers Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUsers(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconUsers Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconUsers(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconUsers Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconVariable_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVariable() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconVariable Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVariable(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconVariable Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVariable(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconVariable Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVariable(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconVariable Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconVideoCamera_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVideoCamera() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconVideoCamera Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVideoCamera(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconVideoCamera Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVideoCamera(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconVideoCamera Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVideoCamera(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconVideoCamera Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconVideoCameraSlash_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVideoCameraSlash() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconVideoCameraSlash Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVideoCameraSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconVideoCameraSlash Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVideoCameraSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconVideoCameraSlash Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconVideoCameraSlash(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconVideoCameraSlash Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconViewColumns_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconViewColumns() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconViewColumns Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconViewColumns(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconViewColumns Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconViewColumns(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconViewColumns Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconViewColumns(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconViewColumns Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconViewfinderCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconViewfinderCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconViewfinderCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconViewfinderCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconViewfinderCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconViewfinderCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconViewfinderCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconViewfinderCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconViewfinderCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconWallet_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWallet() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconWallet Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWallet(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconWallet Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWallet(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconWallet Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWallet(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconWallet Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconWifi_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWifi() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconWifi Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWifi(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconWifi Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWifi(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconWifi Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWifi(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconWifi Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconWindow_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWindow() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconWindow Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWindow(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconWindow Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWindow(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconWindow Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWindow(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconWindow Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconWrench_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWrench() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconWrench Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWrench(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconWrench Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWrench(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconWrench Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWrench(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconWrench Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconWrenchScrewdriver_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWrenchScrewdriver() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconWrenchScrewdriver Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWrenchScrewdriver(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconWrenchScrewdriver Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWrenchScrewdriver(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconWrenchScrewdriver Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconWrenchScrewdriver(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconWrenchScrewdriver Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconXCircle_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconXCircle() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconXCircle Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconXCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconXCircle Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconXCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconXCircle Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconXCircle(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconXCircle Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }

    @Test
    fun heroIconXMark_rendersAllVariantSizePaths() {
        run {
            val html = createHTML(prettyPrint = false).div { heroIconXMark() }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-outline" width="24" height="24">"""),
                "heroIconXMark Outline default: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Sm) }
            assertTrue(
                html.contains("""viewBox="0 0 16 16" class="heroicon-sm heroicon-solid" width="16" height="16">"""),
                "heroIconXMark Solid Sm: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Md) }
            assertTrue(
                html.contains("""viewBox="0 0 20 20" class="heroicon-md heroicon-solid" width="20" height="20">"""),
                "heroIconXMark Solid Md: expected svg open tag not found in: $html",
            )
        }
        run {
            val html = createHTML(prettyPrint = false).div { heroIconXMark(variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra") }
            assertTrue(
                html.contains("""viewBox="0 0 24 24" class="heroicon-lg heroicon-solid custom-extra" width="24" height="24">"""),
                "heroIconXMark Solid Lg + extraClasses: expected svg open tag not found in: $html",
            )
        }
    }
}
