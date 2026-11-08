package io.github.ollin.kdaisyui.gradle

import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.startupcheck.OneShotStartupCheckStrategy
import org.testcontainers.images.builder.Transferable
import org.testcontainers.utility.DockerImageName
import org.testcontainers.utility.MountableFile
import java.io.File
import java.time.Duration

/**
 * The only code that touches Docker: copies the inputs in, runs the command to completion, copies
 * the stylesheet out. No bind mount, so a daemon on another machine serves it as well as a local
 * one, and no docker CLI or Node on the build host.
 */
object NodeContainerRun {
    fun compile(run: StylesheetRun, output: File) {
        val node = GenericContainer<Nothing>(DockerImageName.parse(run.image))
        try {
            node.withCopyToContainer(Transferable.of(run.npmrc), "/deps/.npmrc")
            run.copies.forEach { (host, target) -> node.withCopyToContainer(MountableFile.forHostPath(host.toPath()), target) }
            node.withCommand(*run.command.toTypedArray())
            node.withStartupCheckStrategy(OneShotStartupCheckStrategy().withTimeout(Duration.ofMinutes(5)))
            node.start()
            node.copyFileFromContainer("/deps/project/out/app.css", output.absolutePath)
        } finally {
            node.stop()
        }
    }
}
