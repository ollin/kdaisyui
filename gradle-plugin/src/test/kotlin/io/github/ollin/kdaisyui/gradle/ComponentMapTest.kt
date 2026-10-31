package io.github.ollin.kdaisyui.gradle

import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentMapTest {

    private val map = ComponentMap(
        "# header\n\nbutton\tButtonColor ButtonKt\tbtn btn-primary\ncard\tCardKt\tcard card-body\nstatus\tStatusKt\tstatus\n",
    )

    @Test
    fun theClassesAreThoseOfEveryComponentAFoundClassBelongsTo() {
        assertEquals(listOf("btn", "btn-primary", "status"), map.classesFor(setOf("ButtonColor", "StatusKt")))
    }
}
