import { extractSuffixes, classifySuffixes, toPascalCase, toCamelCase } from './classify.js'
import fs from 'fs'
import path from 'path'

function findCssDir() {
  const candidates = [
    path.resolve(import.meta.dirname, '../node_modules/daisyui/components'),
    path.resolve(import.meta.dirname, '../node_modules/daisyui/src/components'),
    '/tmp/daisyui/packages/daisyui/src/components',
  ]
  for (const c of candidates) {
    if (fs.existsSync(c)) return c
  }
  throw new Error('DaisyUI CSS source not found. Run npm install first.')
}

function readCss(cssDir, cssSource) {
  const p = path.join(cssDir, `${cssSource}.css`)
  return fs.existsSync(p) ? fs.readFileSync(p, 'utf8') : ''
}

function generateEnumBlock(enumName, prefix, values) {
  if (values.length === 0) return ''
  const entries = values.map(v => `    ${toPascalCase(v)}("${prefix}-${v}"),`).join('\n')
  return `\nenum class ${enumName}(internal val className: String) {\n${entries}\n}\n`
}

function generateFunctionBody(fn, prefix, variants, sizes, booleans, indent = '    ') {
  const lines = []
  if (fn.role) lines.push(`${indent}    role = "${fn.role}"`)
  if (fn.fixedInputType) lines.push(`${indent}    type = InputType.${fn.fixedInputType}`)

  const beforeExtras = (fn.extras ?? []).filter(e => e.position === 'before_classes')
  const afterExtras = (fn.extras ?? []).filter(e => e.position !== 'before_classes')

  for (const extra of beforeExtras) {
    for (const line of extra.apply.trim().split('\n')) {
      lines.push(`${indent}    ${line}`)
    }
  }

  const baseClasses = fn.baseClasses ?? [fn.cssClass ?? fn.baseClass ?? prefix]
  lines.push(`${indent}    addClassNames("${baseClasses.join(' ')}")`)

  if (variants.length > 0) lines.push(`${indent}    if (variant != null) addClassNames(variant.className)`)
  if (sizes.length > 0) lines.push(`${indent}    if (size != null) addClassNames(size.className)`)

  for (const b of booleans) {
    lines.push(`${indent}    if (${toCamelCase(b)}) addClassNames("${prefix}-${b}")`)
  }

  for (const extra of afterExtras) {
    for (const line of extra.apply.trim().split('\n')) {
      lines.push(`${indent}    ${line}`)
    }
  }

  lines.push(`${indent}    addClassNames(extraClasses)`)
  if (!fn.noContent) lines.push(`${indent}    if (attrs != null) attrs()`)

  if (!fn.noContent) {
    if (fn.textParam) {
      lines.push(`${indent}    when {`)
      lines.push(`${indent}        content != null -> content()`)
      lines.push(`${indent}        text != null -> +text`)
      lines.push(`${indent}    }`)
    } else {
      lines.push(`${indent}    content()`)
    }
  }

  return lines.join('\n')
}

function generateFunction(fn, prefix, variants, sizes, booleans, componentName) {
  const fnName = fn.name ?? `daisy${componentName}`
  const htmlEl = fn.htmlElement
  const htmlTag = fn.htmlTag
  const receiver = fn.receiver ?? 'FlowContent'

  const extraNames = new Set((fn.extras ?? []).map(e => e.name))
  const filteredBooleans = booleans.filter(b => !extraNames.has(toCamelCase(b)))

  if (fn.textOnly) {
    return `fun ${receiver}.${fnName}(\n    text: String,\n    extraClasses: String? = null,\n) {\n    ${htmlTag} {\n        addClassNames("${fn.cssClass ?? prefix}")\n        addClassNames(extraClasses)\n        +text\n    }\n}`
  }

  const params = []
  if (fn.textParam) params.push('    text: String? = null,')
  if (variants.length > 0) params.push(`    variant: ${componentName}Variant? = null,`)
  if (sizes.length > 0) params.push(`    size: ${componentName}Size? = null,`)
  for (const b of filteredBooleans) params.push(`    ${toCamelCase(b)}: Boolean = false,`)
  for (const extra of (fn.extras ?? [])) params.push(`    ${extra.name}: ${extra.type} = ${extra.default},`)
  params.push('    extraClasses: String? = null,')
  if (!fn.noContent) params.push(`    attrs: (${htmlEl}.() -> Unit)? = null,`)
  if (!fn.noContent) {
    const nullable = fn.textParam ? '? = null' : ''
    params.push(`    content: (${htmlEl}.() -> Unit)${nullable},`)
  }

  const body = generateFunctionBody(fn, prefix, variants, sizes, filteredBooleans)
  return `fun ${receiver}.${fnName}(\n${params.join('\n')}\n) {\n    ${htmlTag} {\n${body}\n    }\n}`
}

export function generateComponent(config, cssDir) {
  const isSingle = !config.functions
  const cssSource = config.cssSource ?? config.component.toLowerCase()
  const cssContent = readCss(cssDir, cssSource)

  const allImports = new Set([
    'kdaisyui.core.addClassNames',
    'kotlinx.html.FlowContent',
  ])

  const addRoleImportIfNeeded = (cfg) => {
    if (cfg.role) allImports.add('kotlinx.html.role')
  }
  const addIdImportIfNeeded = (apply) => {
    if (/\bthis\.id\b/.test(apply ?? '')) allImports.add('kotlinx.html.id')
  }

  let enumBlocks = ''
  let fnBlocks = []

  if (isSingle) {
    const prefix = (config.cssPrefix ?? config.baseClass ?? config.component.toLowerCase())
    const suffixes = extractSuffixes(cssContent, prefix)
    const { variants, sizes, booleans } = classifySuffixes(
      suffixes,
      [],
      config.additionalBooleans ?? [],
    )

    if (variants.length > 0 || sizes.length > 0 || !config.noContent) {
      allImports.add(`kotlinx.html.${config.htmlElement}`)
      allImports.add(`kotlinx.html.${config.htmlTag}`)
    }
    addRoleImportIfNeeded(config)
    if (config.fixedInputType) allImports.add('kotlinx.html.InputType')
    for (const extra of (config.extras ?? [])) {
      for (const imp of (extra.imports ?? [])) allImports.add(imp)
      if (/\binput\s*\{/.test(extra.apply ?? '')) {
        allImports.add('kotlinx.html.input')
        allImports.add('kotlinx.html.INPUT')
      }
      addIdImportIfNeeded(extra.apply)
    }

    enumBlocks += generateEnumBlock(`${config.component}Variant`, prefix, variants)
    enumBlocks += generateEnumBlock(`${config.component}Size`, prefix, sizes)

    const fn = { ...config, name: `daisy${config.component}`, cssClass: prefix }
    fnBlocks.push(generateFunction(fn, prefix, variants, sizes, booleans, config.component))
  } else {
    const fns = config.functions
    const primaryFn = fns[0]
    const primaryPrefix = primaryFn.cssClass
    const cssContent2 = readCss(cssDir, cssSource)

    const siblingClasses = fns.map(f => f.cssClass)

    for (const fn of fns) {
      allImports.add(`kotlinx.html.${fn.htmlElement}`)
      allImports.add(`kotlinx.html.${fn.htmlTag}`)
      if (fn.receiver && fn.receiver !== 'FlowContent') allImports.add(`kotlinx.html.${fn.receiver}`)
      addRoleImportIfNeeded(fn)
      if (fn.fixedInputType) allImports.add('kotlinx.html.InputType')
      for (const extra of (fn.extras ?? [])) {
        for (const imp of (extra.imports ?? [])) allImports.add(imp)
        if (/\binput\s*\{/.test(extra.apply ?? '')) {
          allImports.add('kotlinx.html.input')
          allImports.add('kotlinx.html.INPUT')
        }
        addIdImportIfNeeded(extra.apply)
      }
    }

    const primarySuffixes = extractSuffixes(cssContent2, primaryPrefix)
    const siblingExcludes = siblingClasses
      .filter(c => c !== primaryPrefix)
      .map(c => c.startsWith(primaryPrefix + '-') ? c.slice(primaryPrefix.length + 1) : c)

    const { variants, sizes, booleans } = classifySuffixes(
      primarySuffixes,
      siblingExcludes,
      primaryFn.additionalBooleans ?? [],
    )

    const componentName = config.component
    enumBlocks += generateEnumBlock(`${componentName}Variant`, primaryPrefix, variants)
    enumBlocks += generateEnumBlock(`${componentName}Size`, primaryPrefix, sizes)

    for (const fn of fns) {
      const fnPrefix = fn.cssClass
      let fnVariants = [], fnSizes = [], fnBooleans = []
      if (fn === primaryFn) {
        fnVariants = variants; fnSizes = sizes; fnBooleans = booleans
      } else if (!fn.textOnly && !fn.noContent) {
        const fnSuffixes = extractSuffixes(cssContent2, fnPrefix)
        const fnExclude = siblingClasses.filter(c => c !== fnPrefix)
          .map(c => c.startsWith(fnPrefix + '-') ? c.slice(fnPrefix.length + 1) : c)
        const cl = classifySuffixes(fnSuffixes, fnExclude, fn.additionalBooleans ?? [])
        fnBooleans = cl.booleans
      }
      fnBlocks.push(generateFunction(fn, fnPrefix, fnVariants, fnSizes, fnBooleans, componentName))
    }
  }

  const sortedImports = [...allImports]
    .filter(i => i)
    .sort((a, b) => {
      if (a.startsWith('kdaisyui') && !b.startsWith('kdaisyui')) return -1
      if (!a.startsWith('kdaisyui') && b.startsWith('kdaisyui')) return 1
      return a.localeCompare(b)
    })

  const header = [
    `// GENERATED — DO NOT EDIT`,
    `// Source: codegen/src/config/${config.component.toLowerCase()}.yml + daisyui ${cssSource}.css`,
    `// Regenerate: ./gradlew generateComponents`,
    ``,
    `package kdaisyui.components`,
    ``,
    ...sortedImports.map(i => `import ${i}`),
  ].join('\n')

  const body = [enumBlocks.trimStart(), ...fnBlocks].filter(Boolean).join('\n\n')
  return `${header}\n\n${body}\n`
}
