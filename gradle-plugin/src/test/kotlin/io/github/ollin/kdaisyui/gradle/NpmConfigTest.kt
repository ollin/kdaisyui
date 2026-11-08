package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NpmConfigTest {

    private val mirror = "https://npm.mirror.example/npmjs/"

    @Test
    fun withoutACredentialOnlyTheRegistry() {
        assertEquals("registry=https://registry.npmjs.org/\n", NpmConfig.npmrc("https://registry.npmjs.org/", null, null))
    }

    @Test
    fun aMirrorWithItsCredential() {
        assertEquals(
            "registry=$mirror\n//npm.mirror.example/npmjs/:_auth=dXNlcjpzZWNyZXQ=\n",
            NpmConfig.npmrc(mirror, "user", "secret"),
        )
    }

    @Test
    fun aPasswordWithoutAUserNamesTheUser() {
        val failure = assertFailsWith<IllegalArgumentException> { NpmConfig.npmrc(mirror, null, "secret") }
        assertEquals("kdaisyui: npmPassword is set but npmUser is missing", failure.message)
    }

    @Test
    fun aUserWithoutAPasswordNamesThePassword() {
        val failure = assertFailsWith<IllegalArgumentException> { NpmConfig.npmrc(mirror, "user", null) }
        assertEquals("kdaisyui: npmUser is set but npmPassword is missing", failure.message)
    }
}
