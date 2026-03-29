package kdaisyui.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.webjars.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(Webjars)
        configureRouting()
    }.start(wait = true)
}

fun Application.configureRouting() {
    routing {
        dashboardRoutes()
    }
}
