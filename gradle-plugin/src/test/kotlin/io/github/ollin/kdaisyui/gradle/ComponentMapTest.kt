package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentMapTest {

    private val map = ComponentMap(
        "# header\n\nbutton\tButtonColor ButtonKt\tbtn btn-primary\ncard\tCardKt\tcard card-body\nstatus\tStatusKt\tstatus\n",
    )
    private val all = listOf("btn", "btn-primary", "card", "card-body", "status")

    @Test
    fun theClassesAreThoseOfEveryComponentAFoundClassBelongsTo() {
        assertEquals(Selection(listOf("btn", "btn-primary", "status"), null), map.select(setOf("ButtonColor", "StatusKt")))
    }

    @Test
    fun aClassTheMapDoesNotKnowGetsTheFullListAndIsNamed() {
        assertEquals(Selection(all, "kdaisyui-components.txt does not know AuraKt"), map.select(setOf("ButtonKt", "AuraKt")))
    }

    @Test
    fun noComponentFoundGetsTheFullList() {
        assertEquals(Selection(all, "the compiled classes name no kdaisyui component"), map.select(emptySet()))
    }

    @Test
    fun aClassIsNamedByItsComponentAndAnUnknownClassByNone() {
        assertEquals(listOf("card", null), listOf(map.componentOf("card-body"), map.componentOf("bg-base-100")))
    }
}
