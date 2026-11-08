package io.github.ollin.kdaisyui.gradle

import java.util.Base64

object NpmConfig {
    /**
     * The `.npmrc` the compile copies into the container: the registry, and its `_auth` when a
     * credential is given. Half a credential fails, naming the missing half, before any container.
     */
    fun npmrc(registry: String, user: String?, password: String?): String {
        val registryLine = "registry=$registry\n"
        if (user == null && password == null) return registryLine
        requireNotNull(user) { "kdaisyui: npmPassword is set but npmUser is missing" }
        requireNotNull(password) { "kdaisyui: npmUser is set but npmPassword is missing" }
        val auth = Base64.getEncoder().encodeToString("$user:$password".toByteArray())
        return registryLine + "//${registry.substringAfter("://")}:_auth=$auth\n"
    }
}
