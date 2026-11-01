/**
 * `kdaisyui-components.txt`, the map the Gradle plugin narrows a build's class list with: per
 * component, the JVM classes a consumer's compiled code names when it uses it, and the CSS classes
 * the component can emit. One line each, tab-separated, so no class name needs quoting.
 */

/** Every top-level class a generated file declares, beside the `<Stem>Kt` that holds its functions. */
const DECLARED_CLASS = /^(?:enum class|class) (\w+)/gm

export function componentMapLine(component: string, fileStem: string, kotlin: string, cssClasses: readonly string[]): string {
  const jvmClasses = [`${fileStem}Kt`, ...[...kotlin.matchAll(DECLARED_CLASS)].map(match => match[1])]
  return [component, [...new Set(jvmClasses)].sort().join(' '), [...new Set(cssClasses)].sort().join(' ')].join('\t')
}
