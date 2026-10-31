// Checks on the build itself, run by CI after `check`. Today one: no Gradle problem beyond those
// gradle/allowed-gradle-problems.txt accepts. Not published.
plugins {
    id("kdaisyui.kotlin-library-conventions")
    alias(libs.plugins.kover)
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
