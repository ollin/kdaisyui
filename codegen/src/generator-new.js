import { toPascalCase, toCamelCase } from './classifier.js'

const KOTLIN_KEYWORDS = new Set(['object', 'class', 'fun', 'val', 'var', 'if', 'else', 'when', 'for', 'while', 'return', 'true', 'false', 'null'])

function escapeKotlinKeyword(name) {
  return KOTLIN_KEYWORDS.has(name) ? `_${name}` : name
}

function htmlTagFor(element) {
  const exceptions = { 
    FIELDSET: 'fieldSet', 
    INPUT: 'input',
    TEXTAREA: 'textArea'
  }
  return exceptions[element] ?? element.toLowerCase()
}

function generateEnum(enumName, prefix, values) {
  if (!values || values.length === 0) return ''
  const entries = values.map(v => `    ${toPascalCase(v)}("${prefix}-${v}"),`).join('\n')
  return `enum class ${enumName}(internal val className: String) {\n${entries}\n}\n`
}

function generateVariantEnum(classified) {
  if (classified.colors.length === 0) return ''
  return generateEnum(`${classified.componentName}Variant`, classified.prefix, classified.colors)
}

function generateSizeEnum(classified) {
  if (classified.sizes.length === 0) return ''
  return generateEnum(`${classified.componentName}Size`, classified.prefix, classified.sizes)
}

function getAllBooleanParams(classified, extras, config, componentName) {
  const extraNames = new Set((extras || []).map(e => e.name))
  const booleans = []
  
  for (const s of classified.styles) {
    if (!extraNames.has(toCamelCase(s))) {
      booleans.push(s)
    }
  }
  for (const m of classified.modifiers) {
    if (!extraNames.has(toCamelCase(m))) {
      booleans.push(m)
    }
  }
  for (const b of classified.behaviors) {
    if (!extraNames.has(toCamelCase(b))) {
      booleans.push(b)
    }
  }
  for (const d of classified.directions) {
    if (!extraNames.has(toCamelCase(d))) {
      booleans.push(d)
    }
  }
  for (const p of classified.placements) {
    if (!extraNames.has(toCamelCase(p))) {
      booleans.push(p)
    }
  }
  
  const additionalBooleans = config?.additionalBooleans?.[componentName.toLowerCase()] || []
  for (const b of additionalBooleans) {
    if (!booleans.includes(b) && !extraNames.has(toCamelCase(b))) {
      booleans.push(b)
    }
  }
  
  return booleans.sort()
}

function generateMainFunction(classified, element, config) {
  const { componentName, prefix } = classified
  const htmlTag = htmlTagFor(element)
  const extras = config?.extras?.[classified.componentName.toLowerCase()] || []
  const booleans = getAllBooleanParams(classified, extras, config, componentName)
  const hasTextParam = config?.textParams?.includes(classified.componentName.toLowerCase()) || false
  const noContent = config?.noContent?.includes(classified.componentName.toLowerCase()) || false
  const role = config?.roles?.[classified.componentName.toLowerCase()]
  const fixedInputType = config?.inputTypes?.[classified.componentName.toLowerCase()]
  
  const params = []
  if (hasTextParam) params.push('    text: String? = null,')
  if (classified.colors.length > 0) {
    params.push(`    variant: ${componentName}Variant? = null,`)
  }
  if (classified.sizes.length > 0) {
    params.push(`    size: ${componentName}Size? = null,`)
  }
  for (const b of booleans) {
    params.push(`    ${escapeKotlinKeyword(toCamelCase(b))}: Boolean = false,`)
  }
  for (const extra of extras) {
    params.push(`    ${extra.name}: ${extra.type} = ${extra.default},`)
  }
  params.push('    extraClasses: String? = null,')
  if (!noContent) {
    params.push(`    attrs: (${element}.() -> Unit)? = null,`)
    if (hasTextParam) {
      params.push(`    content: (${element}.() -> Unit)? = null,`)
    } else {
      params.push(`    content: (${element}.() -> Unit),`)
    }
  } else {
    params.push(`    attrs: (${element}.() -> Unit)? = null,`)
  }
  
  const body = generateFunctionBody(classified, element, { extras, role, fixedInputType, hasTextParam, booleans, noContent })
  
  return `fun FlowContent.daisy${componentName}(\n${params.join('\n')}\n) {\n    ${htmlTag} {\n${body}\n    }\n}`
}

function generateFunctionBody(classified, element, options) {
  const { prefix, styles } = classified
  const { extras, role, fixedInputType, hasTextParam, booleans, noContent } = options
  const lines = []
  
  if (role) lines.push(`        role = "${role}"`)
  if (fixedInputType) lines.push(`        type = InputType.${fixedInputType}`)
  
  const beforeExtras = (extras || []).filter(e => e.position === 'before_classes')
  for (const extra of beforeExtras) {
    for (const line of extra.apply.trim().split('\n')) {
      lines.push(`        ${line}`)
    }
  }
  
  lines.push(`        addClassNames("${prefix}")`)
  
  if (classified.colors.length > 0) {
    lines.push(`        if (variant != null) addClassNames(variant.className)`)
  }
  if (classified.sizes.length > 0) {
    lines.push(`        if (size != null) addClassNames(size.className)`)
  }
  
  for (const b of booleans) {
    lines.push(`        if (${escapeKotlinKeyword(toCamelCase(b))}) addClassNames("${prefix}-${b}")`)
  }
  
  const afterExtras = (extras || []).filter(e => e.position !== 'before_classes')
  for (const extra of afterExtras) {
    for (const line of extra.apply.trim().split('\n')) {
      lines.push(`        ${line}`)
    }
  }
  
  lines.push(`        addClassNames(extraClasses)`)
  if (!noContent) {
    lines.push(`        if (attrs != null) attrs()`)
    
    if (hasTextParam) {
      lines.push(`        when {`)
      lines.push(`            content != null -> content()`)
      lines.push(`            text != null -> +text`)
      lines.push(`        }`)
    } else {
      lines.push(`        content()`)
    }
  } else {
    lines.push(`        if (attrs != null) attrs()`)
  }
  
  return lines.join('\n')
}

function generatePartFunction(classified, partClass, element, config) {
  const componentName = classified.componentName
  const partPascal = toPascalCase(stripClassPrefix(classified.prefix, partClass))
  const htmlTag = htmlTagFor(element)
  const hasTextParam = config?.textParams?.includes(partClass) || partClass.includes('title')
  
  const params = []
  if (hasTextParam) params.push('    text: String? = null,')
  params.push('    extraClasses: String? = null,')
  params.push(`    attrs: (${element}.() -> Unit)? = null,`)
  if (hasTextParam) {
    params.push(`    content: (${element}.() -> Unit)? = null,`)
  } else {
    params.push(`    content: (${element}.() -> Unit),`)
  }
  
  const body = []
  body.push(`        addClassNames("${partClass}")`)
  body.push(`        addClassNames(extraClasses)`)
  body.push(`        if (attrs != null) attrs()`)
  
  if (hasTextParam) {
    body.push(`        when {`)
    body.push(`            content != null -> content()`)
    body.push(`            text != null -> +text`)
    body.push(`        }`)
  } else {
    body.push(`        content()`)
  }
  
  return `fun FlowContent.daisy${componentName}${partPascal}(\n${params.join('\n')}\n) {\n    ${htmlTag} {\n${body.join('\n')}\n    }\n}`
}

function stripClassPrefix(prefix, className) {
  if (className.startsWith(prefix + '-')) {
    return className.slice(prefix.length + 1)
  }
  return className
}

function collectImports(classified, element, config) {
  const imports = new Set([
    'com.github.ollin.kdaisyui.core.addClassNames',
    'kotlinx.html.FlowContent',
  ])
  
  imports.add(`kotlinx.html.${element}`)
  imports.add(`kotlinx.html.${htmlTagFor(element)}`)
  
  if (config?.roles?.[classified.componentName.toLowerCase()]) {
    imports.add('kotlinx.html.role')
  }
  if (config?.inputTypes?.[classified.componentName.toLowerCase()]) {
    imports.add('kotlinx.html.InputType')
  }
  
  for (const extra of (config?.extras?.[classified.componentName.toLowerCase()] || [])) {
    for (const imp of (extra.imports || [])) {
      imports.add(imp)
    }
  }
  
  for (const part of classified.parts) {
    const partElement = inferPartElement(part)
    imports.add(`kotlinx.html.${partElement}`)
    imports.add(`kotlinx.html.${htmlTagFor(partElement)}`)
  }
  
  return [...imports].sort((a, b) => {
    if (a.startsWith('kdaisyui') && !b.startsWith('kdaisyui')) return -1
    if (!a.startsWith('kdaisyui') && b.startsWith('kdaisyui')) return 1
    return a.localeCompare(b)
  })
}

export function generateKotlinFile(classified, elementRules, config) {
  const componentName = classified.componentName.toLowerCase()
  const element = elementRules.primaryElement?.toUpperCase() || 'DIV'
  const imports = collectImports(classified, element, config)
  
  const header = [
    `// GENERATED — DO NOT EDIT`,
    `// Source: daisyui/packages/docs/src/routes/(routes)/components/${componentName}/+page.md`,
    `// Regenerate: cd codegen && npm run generate`,
    ``,
    `package com.github.ollin.kdaisyui.components`,
    ``,
    ...imports.map(i => `import ${i}`),
  ].join('\n')
  
  const enums = [
    generateVariantEnum(classified),
    generateSizeEnum(classified),
  ].filter(Boolean).join('\n')
  
  const mainFn = generateMainFunction(classified, element, config)
  
  const partFns = classified.parts.map(partClass => {
    const partElement = inferPartElement(partClass)
    return generatePartFunction(classified, partClass, partElement, config)
  })
  
  const body = [enums, mainFn, ...partFns].filter(Boolean).join('\n\n')
  
  return `${header}\n\n${body}\n`
}

function inferPartElement(partName) {
  if (partName === 'stat-title') return 'DIV'
  if (partName === 'card-title') return 'H2'
  if (partName.includes('title')) return 'H2'
  if (partName.includes('actions')) return 'DIV'
  if (partName.includes('body')) return 'DIV'
  if (partName.includes('header')) return 'DIV'
  if (partName.includes('footer')) return 'DIV'
  if (partName.includes('side')) return 'DIV'
  if (partName.includes('overlay')) return 'LABEL'
  if (partName.includes('content')) return 'DIV'
  return 'DIV'
}
