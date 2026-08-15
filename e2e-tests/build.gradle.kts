plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    testImplementation(project(":lib"))
    testImplementation(project(":example-app"))
    testImplementation(libs.ktor.server.core)
    testImplementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.test.host)

    testImplementation(libs.playwright)

    // Kotest (existing tests)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Cucumber BDD
    testImplementation(libs.cucumber.java)
    testImplementation(libs.cucumber.junit.platform.engine)
    testImplementation(libs.cucumber.picocontainer)

    testImplementation(libs.junit.platform.suite)
    testRuntimeOnly(libs.junit.platform.suite.engine)

    testImplementation(libs.logback.classic)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
    dependsOn(":example-app:classes")

    // Cucumber naming strategy for disambiguating scenarios in Gradle output
    systemProperty("cucumber.junit-platform.naming-strategy", "long")

    environment("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1")
    val chromiumPath = providers.environmentVariable("PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH")
        .orElse(providers.provider {
            listOf("chromium", "chromium-browser", "google-chrome-stable", "google-chrome")
                .firstNotNullOfOrNull { name ->
                    ProcessBuilder("which", name).start()
                        .inputStream.bufferedReader().readLine()?.takeIf { it.isNotBlank() }
                }
        })
    chromiumPath.orNull?.let { environment("PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH", it) }

    doLast {
        listOf("playwright-report", "test-results").forEach { name ->
            val dir = projectDir.resolve(name)
            if (dir.isDirectory && dir.walkBottomUp().all { it.isDirectory }) {
                dir.deleteRecursively()
            }
        }
    }
}


tasks.register<JavaExec>("playwrightInstall") {
    classpath(sourceSets["test"].runtimeClasspath)
    mainClass.set("com.microsoft.playwright.CLI")
    args("install", "chromium", "--with-deps")
}
