package io.github.ollin.kdaisyui.gradle

import com.github.dockerjava.api.model.Bind
import com.github.dockerjava.api.model.Volume
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
    const val NPM_CACHE_VOLUME = "kdaisyui-npm-cache"

    fun compile(run: StylesheetRun, output: File) {
        val node = GenericContainer<Nothing>(DockerImageName.parse(run.image))
        try {
            node.withCopyToContainer(Transferable.of(run.npmrc), "/deps/.npmrc")
            run.copies.forEach { (host, target) -> node.withCopyToContainer(MountableFile.forHostPath(host.toPath()), target) }
            // npm's cache in a named volume on the daemon, so a second compile installs nothing anew.
            node.withCreateContainerCmdModifier { it.hostConfig!!.withBinds(Bind(NPM_CACHE_VOLUME, Volume("/root/.npm"))) }
            node.withCommand(*run.command.toTypedArray())
            node.withStartupCheckStrategy(OneShotStartupCheckStrategy().withTimeout(Duration.ofMinutes(5)))
            node.start()
            node.copyFileFromContainer("/deps/project/out/app.css", output.absolutePath)
        } finally {
            node.stop()
        }
    }
}
