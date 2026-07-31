package io.github.ollin.kdaisyui.codegen

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.nio.file.Path
import kotlin.io.path.readText

/**
 * Reads a DaisyUI component page and turns its YAML frontmatter into a [Component].
 *
 * The JavaScript equivalent (`codegen/src/parser/frontmatter.js`) hand-rolls a ~100-line
 * YAML state machine with four hardcoded indent levels — while `js-yaml` sits declared and
 * unused in its own package.json. Here a real parser does it.
 */
object Frontmatter {

    private val yaml = Yaml(configuration = YamlConfiguration(strictMode = false))

    private val FENCE = Regex("^---\\s*$\\n(.*?)^---\\s*$", setOf(RegexOption.MULTILINE, RegexOption.DOT_MATCHES_ALL))

    fun parseFile(page: Path, element: String): Component = parse(page.readText(), element)

    fun parse(page: String, element: String): Component {
        val raw = FENCE.find(page)?.groupValues?.get(1)
            ?: error("No YAML frontmatter fence")

        val doc = yaml.decodeFromString(Page.serializer(), raw)
        val base = doc.classnames[COMPONENT_CATEGORY]?.firstOrNull()?.cssClass
            ?: error("No `component` class for ${doc.title}")

        return Component(
            name = doc.title,
            baseClass = base,
            description = doc.desc.orEmpty(),
            element = element,
            axes = doc.classnames.toAxes(),
        )
    }

    private fun Map<String, List<Entry>>.toAxes(): List<Axis> =
        filterKeys { it !in STRUCTURAL_CATEGORIES }
            .map { (category, entries) ->
                val options = entries.map { it.toOption() }
                if (category in EXCLUSIVE_CATEGORIES) {
                    Axis.Choice(category, options)
                } else {
                    Axis.Flags(category, options)
                }
            }

    private const val COMPONENT_CATEGORY = "component"

    @Serializable
    private data class Page(
        val title: String,
        val desc: String? = null,
        val classnames: Map<String, List<Entry>> = emptyMap(),
    )

    @Serializable
    private data class Entry(
        @SerialName("class") val cssClass: String,
        val desc: String? = null,
        val default: Boolean = false,
    ) {
        fun toOption() = ClassOption(cssClass, desc.orEmpty(), default)
    }
}
