package kdaisyui.example

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import kotlinx.html.stream.appendHTML

fun HTMLTag.hxGet(url: String) { attributes["hx-get"] = url }
fun HTMLTag.hxTrigger(trigger: String) { attributes["hx-trigger"] = trigger }
fun HTMLTag.hxSwap(strategy: String) { attributes["hx-swap"] = strategy }

suspend fun RoutingCall.respondHtmlFragment(block: TagConsumer<*>.() -> Unit) {
    val html = buildString { appendHTML(prettyPrint = false).apply(block) }
    respondText(html, ContentType.Text.Html)
}
