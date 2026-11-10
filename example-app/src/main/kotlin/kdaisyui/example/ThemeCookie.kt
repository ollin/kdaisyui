package kdaisyui.example

import io.github.ollin.kdaisyui.themes.DaisyTheme
import io.ktor.http.Cookie

/**
 * The profile's chosen theme, kept for a year. `HttpOnly`, because only the server reads it, to
 * render `data-theme`; `SameSite=Lax`, so a link from elsewhere still arrives in the user's theme.
 */
fun themeCookie(theme: DaisyTheme) =
    Cookie("theme", theme.value, maxAge = 31_536_000, path = "/", httpOnly = true, extensions = mapOf("SameSite" to "Lax"))
