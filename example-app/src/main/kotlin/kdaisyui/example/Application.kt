package kdaisyui.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import io.ktor.server.webjars.*
import io.ktor.server.plugins.defaultheaders.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        configureRouting()
    }.start(wait = true)
}

const val CONTENT_SECURITY_POLICY = "default-src 'self'; img-src 'self' data:"

fun Application.configureRouting() {
    // kdaisyui's markup works under this policy (content-security-policy spec); `data:` images only
    // because DaisyUI's own masks and noise are data: SVGs, which are fetched from nowhere.
    install(DefaultHeaders) { header("Content-Security-Policy", CONTENT_SECURITY_POLICY) }
    install(Webjars)
    install(Resources)
    routing {
        // The stylesheet the build compiles, produced by the kdaisyui plugin's kdaisyuiStylesheet task.
        staticResources("/static", "static")
        dashboardRoutes()
    }
}
