package io.github.ollin.kdaisyui.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class HtmlIdTest {

    class AppIds : AnnotatedIdBase("app") {
        class Sidebar(parent: AppIds = AppIds()) : AnnotatedIdBase("sidebar", parent) {
            class Nav(parent: Sidebar = Sidebar()) : AnnotatedIdBase("nav", parent)

            class Item(name: String, parent: Sidebar = Sidebar()) : NamedAnnotatedIdBase("item", name, parent)

            class HtmlIdNamed(name: HtmlId, parent: Sidebar = Sidebar()) :
                NamedAnnotatedIdBase("item", name, parent)
        }

        class Content(parent: AppIds = AppIds()) : AnnotatedIdBase("content", parent)
    }

    @Test
    fun singleSegmentId() {
        val root = AppIds()
        assertEquals("app", root.id)
    }

    @Test
    fun twoLevelHierarchy() {
        val sidebar = AppIds.Sidebar()
        assertEquals("app-sidebar", sidebar.id)
    }

    @Test
    fun threeLevelHierarchy() {
        val nav = AppIds.Sidebar.Nav()
        assertEquals("app-sidebar-nav", nav.id)
    }

    @Test
    fun targetReturnsCssSelector() {
        val nav = AppIds.Sidebar.Nav()
        assertEquals("#app-sidebar-nav", nav.target)
    }

    @Test
    fun targetGlobalReturnsGlobalCssSelector() {
        val nav = AppIds.Sidebar.Nav()
        assertEquals("global #app-sidebar-nav", nav.targetGlobal)
    }

    @Test
    fun namedIdWithSuffix() {
        val item = AppIds.Sidebar.Item("1")
        assertEquals("app-sidebar-item-1", item.id)
    }

    @Test
    fun namedIdsWithDifferentNamesAreNotEqual() {
        val item1 = AppIds.Sidebar.Item("settings")
        val item2 = AppIds.Sidebar.Item("logout")
        assertNotEquals(item1.id, item2.id)
        assertEquals("app-sidebar-item-settings", item1.id)
        assertEquals("app-sidebar-item-logout", item2.id)
    }

    @Test
    fun namedIdWithEmptyNameOmitsSuffix() {
        val item = AppIds.Sidebar.Item("")
        assertEquals("app-sidebar-item", item.id)
    }

    @Test
    fun namedIdAcceptsHtmlIdAsName() {
        val nav = AppIds.Sidebar.Nav()
        val item = AppIds.Sidebar.Item(name = nav.id, parent = AppIds.Sidebar())
        assertEquals("app-sidebar-item-app-sidebar-nav", item.id)
    }

    @Test
    fun htmlIdFactoryCreatesBasicId() {
        val id = htmlId("submit-btn")
        assertEquals("submit-btn", id.id)
        assertEquals("#submit-btn", id.target)
        assertEquals("global #submit-btn", id.targetGlobal)
    }

    @Test
    fun htmlIdToStringReturnsId() {
        val id = htmlId("my-element")
        assertEquals("my-element", id.toString())
    }

    @Test
    fun equalityBasedOnIdValue() {
        val id1 = AppIds.Sidebar.Nav()
        val id2 = AppIds.Sidebar.Nav()
        assertEquals(id1, id2)
        assertEquals(id1.hashCode(), id2.hashCode())
    }

    @Test
    fun differentHierarchicalIdsAreNotEqual() {
        val nav = AppIds.Sidebar.Nav()
        val content = AppIds.Content()
        assertNotEquals<HtmlId>(nav, content)
    }

    @Test
    fun htmlIdEqualsAnnotatedIdBaseWhenIdsMatch() {
        val annotated = AppIds.Content()
        val plain = htmlId("app-content")
        assertEquals(annotated, plain)
    }

    @Test
    fun namedIdEqualsHtmlIdWhenIdsMatch() {
        val named = AppIds.Sidebar.Item("x")
        val plain = htmlId("app-sidebar-item-x")
        assertEquals(named, plain)
    }

    class PassThroughParent : AnnotatedIdBase("parent") {
        class Child(parent: PassThroughParent = PassThroughParent()) : AnnotatedIdBase("", parent)
    }

    @Test
    fun emptySegmentPassesThroughParentId() {
        val child = PassThroughParent.Child()
        assertEquals("parent", child.id)
    }

    @Test
    fun annotatedIdToStringReturnsId() {
        val nav = AppIds.Sidebar.Nav()
        assertEquals("app-sidebar-nav", nav.toString())
    }

    @Test
    fun htmlIdToStringReturnsIdString() {
        val id = htmlId("test-id")
        assertEquals("test-id", id.toString())
    }

    @Test
    fun annotatedIdEqualsItself() {
        val nav = AppIds.Sidebar.Nav()
        assertTrue(nav.equals(nav))
    }

    @Test
    fun annotatedIdNotEqualToNonHtmlId() {
        val nav = AppIds.Sidebar.Nav()
        assertFalse(nav.equals("app-sidebar-nav"))
    }

    @Test
    fun namedIdSecondaryConstructorAcceptsHtmlId() {
        val item = AppIds.Sidebar.HtmlIdNamed(htmlId("x"))
        assertEquals("app-sidebar-item-x", item.id)
    }

    @Test
    fun namedIdEqualsItself() {
        val item = AppIds.Sidebar.Item("1")
        assertTrue(item.equals(item))
    }

    @Test
    fun namedIdNotEqualToNonHtmlId() {
        val item = AppIds.Sidebar.Item("1")
        assertFalse(item.equals("app-sidebar-item-1"))
    }

    @Test
    fun namedIdsWithDifferentIdsAreNotEqual() {
        val item1 = AppIds.Sidebar.Item("a")
        val item2 = AppIds.Sidebar.Item("b")
        assertNotEquals<HtmlId>(item1, item2)
    }

    @Test
    fun namedIdHashCodeMatchesIdHashCode() {
        val item = AppIds.Sidebar.Item("1")
        assertEquals(item.id.hashCode(), item.hashCode())
    }

    @Test
    fun stringHtmlIdsWithSameValueAreEqual() {
        assertEquals(htmlId("dup"), htmlId("dup"))
        assertEquals(htmlId("dup").hashCode(), htmlId("dup").hashCode())
    }

    @Test
    fun unboxedStringHtmlIdUsesValueClassTargetImpl() {
        val id = StringHtmlId("widget")
        assertEquals("#widget", id.target)
        assertEquals("global #widget", id.targetGlobal)
    }

    class SuperCallingId(override val id: String) : HtmlId {
        fun inheritedTarget(): String = super.target
        fun inheritedTargetGlobal(): String = super.targetGlobal
    }

    @Test
    fun defaultTargetReachableViaSuperCall() {
        val id = SuperCallingId("widget")
        assertEquals("#widget", id.inheritedTarget())
        assertEquals("global #widget", id.inheritedTargetGlobal())
    }
}