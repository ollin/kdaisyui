// Checks on the build itself, run by CI after `check`. Today one: no Gradle problem beyond those
// gradle/allowed-gradle-problems.txt accepts. Not published.
plugins {
    id("kdaisyui.kotlin-library-conventions")
    alias(libs.plugins.kover)
    alias(libs.plugins.pitest)
}

// Mutation testing at 100% test strength, as for :lib and :doc-snippets; CI's mutation-tests job
// runs it. lib/build.gradle.kts holds why it is test strength and not mutation score.
pitest {
    targetClasses.set(setOf("io.github.ollin.kdaisyui.buildchecks.*"))
    targetTests.set(setOf("io.github.ollin.kdaisyui.buildchecks.*"))
    junit5PluginVersion.set(libs.versions.pitest.junit5.get())
    avoidCallsTo.set(setOf("kotlin.jvm.internal"))
    outputFormats.set(setOf("HTML", "XML"))
    testStrengthThreshold.set(100)
}

// Reads the report a PREVIOUS Gradle invocation wrote: Gradle writes it when a build ends, so no
// task of the build being checked can read its own. CI runs `check`, then this.
tasks.register<JavaExec>("checkGradleProblems") {
    description = "Fails on a Gradle problem gradle/allowed-gradle-problems.txt does not accept."
    group = "verification"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("io.github.ollin.kdaisyui.buildchecks.CheckGradleProblemsKt")
    args(
        rootProject.layout.buildDirectory.file("reports/problems/problems-report.html").get().asFile.absolutePath,
        rootProject.file("gradle/allowed-gradle-problems.txt").absolutePath,
    )
}
