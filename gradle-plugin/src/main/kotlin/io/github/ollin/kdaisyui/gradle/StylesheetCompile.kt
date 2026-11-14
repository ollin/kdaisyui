package io.github.ollin.kdaisyui.gradle

import java.io.File

/** What runs inside the Node container: pure, so it is tested without Docker. */
object StylesheetCompile {
    /**
     * Installs the pinned Tailwind and DaisyUI beside the project, so the entry's `@plugin "daisyui"`
     * resolves by walking up from `/deps/project`, then compiles `css/app.css` into `out/app.css`.
     * `--prefer-offline` makes a second run a hit in npm's cache.
     */
    fun command(tailwind: String, daisyui: String, minify: Boolean): List<String> {
        val install = "npm install --userconfig /deps/.npmrc --no-fund --no-audit --loglevel=error"
        val packages = "@tailwindcss/cli@$tailwind tailwindcss@$tailwind daisyui@$daisyui"
        return listOf(
            "sh", "-c",
            "cd /deps && ($install --prefer-offline $packages || $install $packages) " +
                "&& cd project && mkdir -p out && /deps/node_modules/.bin/tailwindcss --input css/app.css --output out/app.css" +
                if (minify) " --minify" else "",
        )
    }

    /** Each source directory's place in the container, indexed so two directories with one name cannot collide. */
    fun mountPoints(sources: List<File>): Map<File, String> =
        sources.withIndex().associate { (index, source) -> source to "/deps/project/sources/$index-${source.name}" }
}
