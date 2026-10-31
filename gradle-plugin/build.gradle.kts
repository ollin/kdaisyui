// Narrows kdaisyui's class list to the components a build's compiled code uses. Published to Maven
// Central with its plugin marker; the root build stages both.
plugins {
    `kotlin-dsl`
    alias(libs.plugins.kover)
}

group = "io.github.ollin.kdaisyui"

gradlePlugin {
    plugins {
        create("kdaisyui") {
            id = "io.github.ollin.kdaisyui"
            implementationClass = "io.github.ollin.kdaisyui.gradle.KdaisyuiPlugin"
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test { useJUnitPlatform() }

// Its own 100% line and branch gate: the root build's aggregated one cannot reach an included build.
kover {
    reports {
        verify {
            rule("100% line coverage") { bound { minValue = 100; coverageUnits = kotlinx.kover.gradle.plugin.dsl.CoverageUnit.LINE } }
            rule("100% branch coverage") { bound { minValue = 100; coverageUnits = kotlinx.kover.gradle.plugin.dsl.CoverageUnit.BRANCH } }
        }
    }
}

tasks.check { dependsOn(tasks.named("koverVerify")) }
