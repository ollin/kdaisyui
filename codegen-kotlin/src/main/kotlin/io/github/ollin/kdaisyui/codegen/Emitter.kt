package io.github.ollin.kdaisyui.codegen

import com.squareup.kotlinpoet.BOOLEAN
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.UNIT

/** Emits a component as Kotlin source. Structure only — KotlinPoet owns the formatting. */
object Emitter {

    private const val PACKAGE = "io.github.ollin.kdaisyui.components"
    private const val HTML = "kotlinx.html"
    private val FLOW_CONTENT = ClassName(HTML, "FlowContent")
    private val HTML_ID = ClassName("io.github.ollin.kdaisyui.core", "HtmlId")

    fun emit(component: Component): FileSpec {
        val element = ClassName(HTML, component.element)
        val file = FileSpec.builder(PACKAGE, component.name)
            .addFileComment("Generated from DaisyUI docs. Do not edit.")

        component.choices.forEach { file.addType(enumFor(component, it)) }
        file.addFunction(functionFor(component, element))
        return file.build()
    }

    /** One mutually exclusive axis becomes one enum — illegal combinations stop compiling. */
    private fun enumFor(component: Component, axis: Axis.Choice): TypeSpec {
        val builder = TypeSpec.enumBuilder(enumName(component, axis))
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addParameter("cssClass", STRING)
                    .build(),
            )
            .addProperty(
                PropertySpec.builder("cssClass", STRING)
                    .initializer("cssClass")
                    .build(),
            )
        axis.default?.let {
            builder.addKdoc("DaisyUI default: %L.\n", it.cssClass)
        }
        axis.options.forEach { option ->
            builder.addEnumConstant(
                constantName(component, option),
                TypeSpec.anonymousClassBuilder()
                    .addSuperclassConstructorParameter("%S", option.cssClass)
                    .apply { if (option.description.isNotBlank()) addKdoc("%L\n", option.description) }
                    .build(),
            )
        }
        return builder.build()
    }

    private fun functionFor(component: Component, element: ClassName): FunSpec {
        val block = LambdaTypeName.get(receiver = element, returnType = UNIT).copy(nullable = true)
        val fn = FunSpec.builder("daisy${component.name}")
            .receiver(FLOW_CONTENT)
            .addKdoc("%L\n", component.description)

        component.choices.forEach { axis ->
            fn.addParameter(
                ParameterSpec.builder(axis.category, ClassName(PACKAGE, enumName(component, axis)).copy(nullable = true))
                    .defaultValue("null")
                    .build(),
            )
        }
        component.flags.flatMap { it.options }.forEach { option ->
            fn.addParameter(
                ParameterSpec.builder(flagName(component, option), BOOLEAN)
                    .defaultValue("false")
                    .build(),
            )
        }
        fn.addParameter(ParameterSpec.builder("text", STRING.copy(nullable = true)).defaultValue("null").build())
        fn.addParameter(ParameterSpec.builder("id", HTML_ID.copy(nullable = true)).defaultValue("null").build())
        fn.addParameter(ParameterSpec.builder("extraClasses", STRING.copy(nullable = true)).defaultValue("null").build())
        fn.addParameter(ParameterSpec.builder("content", block).defaultValue("null").build())

        return fn.addCode(bodyFor(component)).build()
    }

    private fun bodyFor(component: Component): CodeBlock {
        val body = CodeBlock.builder()
            .beginControlFlow("val classes = buildList")
            .addStatement("add(%S)", component.baseClass)

        component.choices.forEach { axis ->
            body.addStatement("%L?.let { add(it.cssClass) }", axis.category)
        }
        component.flags.flatMap { it.options }.forEach { option ->
            body.addStatement("if (%L) add(%S)", flagName(component, option), option.cssClass)
        }
        body.addStatement("extraClasses?.let { add(it) }")
        body.endControlFlow()

        return body
            .beginControlFlow("%L(classes = classes.joinToString(%S))", tagFunctionFor(component.element), " ")
            .addStatement("id?.let { this.id = it.value }")
            .addStatement("text?.let { +it }")
            .addStatement("content?.invoke(this)")
            .endControlFlow()
            .build()
    }

    /**
     * The kotlinx.html builder function for an element. Usually the lowercased name, but
     * kotlinx.html spells a few of them camelCase — `textarea` and `fieldset` do not exist.
     */
    private fun tagFunctionFor(element: String) =
        CAMEL_CASE_TAGS[element] ?: element.lowercase()

    private val CAMEL_CASE_TAGS = mapOf(
        "TEXTAREA" to "textArea",
        "FIELDSET" to "fieldSet",
        "OPTGROUP" to "optGroup",
    )

    private fun enumName(component: Component, axis: Axis.Choice) =
        component.name + axis.category.replaceFirstChar(Char::uppercase)

    /** `btn-primary` on Button becomes `Primary`. */
    private fun constantName(component: Component, option: ClassOption) =
        option.cssClass.removePrefix("${component.baseClass}-").pascalCase()

    /** `btn-wide` on Button becomes `wide`. */
    private fun flagName(component: Component, option: ClassOption) =
        option.cssClass.removePrefix("${component.baseClass}-").camelCase()

    private fun String.pascalCase() = split('-').joinToString("") { it.replaceFirstChar(Char::uppercase) }

    private fun String.camelCase() = pascalCase().replaceFirstChar(Char::lowercase)
}
