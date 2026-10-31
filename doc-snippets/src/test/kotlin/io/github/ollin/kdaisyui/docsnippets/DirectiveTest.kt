package io.github.ollin.kdaisyui.docsnippets

import kotlin.test.Test
import kotlin.test.assertEquals

class DirectiveTest {

    @Test
    fun noMarkerCompilesTheSnippet() = assertEquals(Directive.Compile, directiveOf(null))

    @Test
    fun inNamesTheReceiver() = assertEquals(Directive.In("Route"), directiveOf("in Route"))

    @Test
    fun skipCarriesItsReason() = assertEquals(Directive.Skip("the 0.7.x API"), directiveOf("skip - the 0.7.x API"))

    @Test
    fun aSkipWithoutAReasonIsInvalid() =
        assertEquals(Directive.Invalid("skip needs a reason: <!-- snippet: skip - <reason> -->"), directiveOf("skip"))

    @Test
    fun anUnknownDirectiveIsInvalid() =
        assertEquals(Directive.Invalid("unknown snippet directive 'ignore'; known: in <Receiver>, skip - <reason>"), directiveOf("ignore"))
}
