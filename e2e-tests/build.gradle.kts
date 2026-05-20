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
    val ktorVersion = project.property("versions.ktor").toString()
    testImplementation(project(":example-app"))
    testImplementation("io.ktor:ktor-server-core:$ktorVersion")
    testImplementation("io.ktor:ktor-server-netty:$ktorVersion")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")

    testImplementation("com.microsoft.playwright:playwright:1.60.0")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("ch.qos.logback:logback-classic:1.5.32")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
    dependsOn(":example-app:classes")

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
}


tasks.register<JavaExec>("playwrightInstall") {
    classpath(sourceSets["test"].runtimeClasspath)
    mainClass.set("com.microsoft.playwright.CLI")
    args("install", "chromium", "--with-deps")
}
