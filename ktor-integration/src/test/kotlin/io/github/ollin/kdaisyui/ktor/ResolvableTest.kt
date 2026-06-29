package io.github.ollin.kdaisyui.ktor

import io.github.ollin.kdaisyui.core.AnnotatedIdBase
import io.github.ollin.kdaisyui.core.htmlId
import io.ktor.resources.Resource
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.resources.Resources
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertIs

class AppIds : AnnotatedIdBase("app") {
    class Sidebar(parent: AppIds = AppIds()) : AnnotatedIdBase("sidebar", parent)
    class Content(parent: AppIds = AppIds()) : AnnotatedIdBase("content", parent)
}

@Resource("/dashboard")
class DashboardResource

class ResolvableTest {

    @Test
    fun stringResolvableHoldsHref() {
        assertEquals("/dashboard", StringResolvable("/dashboard").href)
    }

    @Test
    fun stringResolvableIsResolvable() {
        assertIs<Resolvable>(StringResolvable("/settings"))
    }

    @Test
    fun htmlIdAsResolvable() {
        val resolvable = htmlId("submit-btn").asResolvable()
        assertEquals("#submit-btn", resolvable.href)
    }

    @Test
    fun annotatedIdAsResolvable() {
        val resolvable = AppIds.Sidebar().asResolvable()
        assertEquals("#app-sidebar", resolvable.href)
    }

    @Test
    fun nestedAnnotatedIdAsResolvable() {
        val resolvable = AppIds.Content().asResolvable()
        assertEquals("#app-content", resolvable.href)
    }

    @Test
    fun stringAsResolvable() {
        val resolvable = "/custom-path".asResolvable()
        assertEquals("/custom-path", resolvable.href)
    }

    @Test
    fun htmlIdHref() {
        assertEquals("#nav-menu", htmlId("nav-menu").href)
    }

    @Test
    fun annotatedIdHref() {
        assertEquals("#app-sidebar", AppIds.Sidebar().href)
    }

    @Test
    fun htmlIdAsResolvableIsStringResolvable() {
        assertIs<StringResolvable>(htmlId("test").asResolvable())
    }

    @Test
    fun stringAsResolvableIsStringResolvable() {
        assertIs<StringResolvable>("/path".asResolvable())
    }

    @Test
    fun resolvableHrefMatchesIdTarget() {
        val sidebar = AppIds.Sidebar()
        assertEquals(sidebar.target, sidebar.asResolvable().href)
    }

    @Test
    fun contextAsResolvableCoversAllBranches() = withResolutionContext {
        val existing: Resolvable = StringResolvable("/dashboard")
        assertEquals(existing, resolveAnyViaContext(existing))
        assertEquals("#submit", resolveAnyViaContext(htmlId("submit")).href)
        assertEquals("/custom", resolveAnyViaContext("/custom").href)
        assertFails { resolveAnyViaContext(Any()) }
        assertEquals("/dashboard", DashboardResource().asResolvable().href)
    }

    @Test
    fun contextHrefCoversAllBranches() = withResolutionContext {
        assertEquals("/dashboard", hrefOfAnyViaContext(StringResolvable("/dashboard")))
        assertEquals("#submit", hrefOfAnyViaContext(htmlId("submit")))
        assertEquals("/custom", hrefOfAnyViaContext("/custom"))
        assertFails { hrefOfAnyViaContext(Any()) }
        assertEquals("/dashboard", DashboardResource().href)
    }
}

private fun withResolutionContext(assertions: ApplicationResolution.() -> Unit) = testApplication {
    lateinit var app: Application
    application {
        install(Resources)
        app = this
    }
    startApplication()
    ApplicationResolution(app).assertions()
}

context(appResolution: ApplicationResolution)
private fun resolveAnyViaContext(value: Any): Resolvable = value.asResolvable()

context(appResolution: ApplicationResolution)
private fun hrefOfAnyViaContext(value: Any): String = value.href
