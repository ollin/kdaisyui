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

/** The whole `kdaisyui-components.txt` for the given lines, sorted under its header. */
export function componentMapFile(lines: readonly string[]): string {
  const header = [
    '# Per component: its name, the JVM classes a consumer names when using it, and the CSS classes',
    '# it can emit, tab-separated. Read by the kdaisyui Gradle plugin. Generated - do not edit.',
    '',
  ]
  return `${header.join('\n')}${[...lines].sort().join('\n')}\n`
}

/** The whole `kdaisyui-classes.txt`: every class a component can emit, once each, sorted. */
export function classListFile(classes: readonly string[]): string {
  const header = [
    '# Every DaisyUI class kdaisyui can emit. Generated - do not edit.',
    '#',
    '# Point your Tailwind @source at this file. Scanning your own sources is not enough:',
    '# a class like btn-primary is assembled at runtime from ButtonColor.Primary, so it',
    '# appears nowhere in your code, and the published jar carries no Kotlin sources.',
    '',
  ]
  return `${header.join('\n')}${[...new Set(classes)].sort().join('\n')}\n`
}
