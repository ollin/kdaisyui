import fs from 'fs'
import path from 'path'
import { getAllComponentDirs, readComponentFrontmatter, toPascalCase } from './parser/frontmatter.js'
import { toCamelCase } from './classifier.js'

const DOCS_DIR = path.resolve(import.meta.dirname, '../../daisyui/packages/docs/src/routes/(routes)/components')
// Committed generated root — a sibling of lib/src/, never inside it.
// Gradle passes --output-dir explicitly; this default is for a bare `node` run.
const DEFAULT_OUTPUT_DIR = path.resolve(import.meta.dirname, '../../lib/generated/test/kotlin/io/github/ollin/kdaisyui/components')

function parseArg(flag, fallback) {
  const prefix = `--${flag}=`
  for (const arg of process.argv) {
    if (arg.startsWith(prefix)) {
      return arg.slice(prefix.length)
    }
  }
  return fallback
}

const OUTPUT_DIR = parseArg('output-dir', DEFAULT_OUTPUT_DIR)
const CONFIG_PATH = path.resolve(import.meta.dirname, '../codegen-config.json')

function loadConfig() {
  if (!fs.existsSync(CONFIG_PATH)) {
    return { skip: [] }
  }
  return JSON.parse(fs.readFileSync(CONFIG_PATH, 'utf8'))
}

function parseTestCases(content) {
  const testCases = []
  const lines = content.split('\n')
  
  let currentTest = null
  let inCodeBlock = false
  let codeBlock = []
  let codeBlockLang = null
  
  for (let i = 0; i < lines.length; i++) {
    const line = lines[i]
    
    const testMatch = line.match(/^### ~(.+)$/)
    if (testMatch) {
      if (currentTest && codeBlock.length > 0) {
        currentTest.html = codeBlock.join('\n')
        testCases.push(currentTest)
      }
      
      currentTest = {
        name: testMatch[1].trim(),
        html: null
      }
      codeBlock = []
      continue
    }
    
    if (line.startsWith('```')) {
      if (!inCodeBlock) {
        inCodeBlock = true
        codeBlockLang = line.slice(3).trim()
        codeBlock = []
      } else {
        inCodeBlock = false
        if (codeBlockLang === 'html' && currentTest) {
          currentTest.html = codeBlock.join('\n')
          testCases.push(currentTest)
          currentTest = null
        }
        codeBlock = []
      }
      continue
    }
    
    if (inCodeBlock) {
      codeBlock.push(line)
    }
  }
  
  if (currentTest && codeBlock.length > 0 && codeBlockLang === 'html') {
    currentTest.html = codeBlock.join('\n')
    testCases.push(currentTest)
  }
  
  return testCases
}

function extractDaisyClasses(html) {
  const classes = []
  const match = html.match(/\$\$([a-z-]+)/g)
  if (match) {
    for (const m of match) {
      classes.push(m.slice(2))
    }
  }
  return [...new Set(classes)]
}

function toClassName(componentName) {
  return toPascalCase(componentName)
}

function buildClassMappings(frontmatter, componentName) {
  const allowedClasses = new Set()
  const classToParam = {}
  const paramToGeneratedClass = {}
  
  const componentClass = frontmatter.classnames?.component?.[0]?.class
  if (componentClass) {
    allowedClasses.add(componentClass)
  }
  
  const categories = ['placement', 'modifier', 'direction', 'behavior', 'style']
  for (const cat of categories) {
    const items = frontmatter.classnames?.[cat]
    if (items && Array.isArray(items)) {
      for (const item of items) {
        if (item.class) {
          allowedClasses.add(item.class)
          const suffix = item.class.replace(`${componentClass}-`, '')
          const paramName = toCamelCase(suffix)
          classToParam[item.class] = paramName
          paramToGeneratedClass[paramName] = item.class
        }
      }
    }
  }
  
  return { allowedClasses, classToParam, paramToGeneratedClass, componentClass }
}

function filterContainerClasses(classes, allowedClasses) {
  return classes.filter(c => allowedClasses.has(c))
}

function toTestFunctionName(name) {
  let funcName = name
    .toLowerCase()
    .replace(/[^a-z0-9]+/g, '_')
    .replace(/^_|_$/g, '')
    .replace(/_+/g, '_')
  
  if (/^[0-9]/.test(funcName)) {
    funcName = `test_${funcName}`
  }
  
  return funcName
}

function mapClassesToParams(classes, classToParam) {
  const params = {}
  for (const cls of classes) {
    if (classToParam[cls]) {
      params[classToParam[cls]] = true
    }
  }
  return params
}

function customPartAssertions(part, tag) {
  const assertions = [`        assertTrue(html.contains("<${tag}"))`]
  if (part.cssClass) {
    assertions.push(`        assertTrue(html.contains("class=\\"${part.cssClass}"))`)
  }
  for (const [name, value] of Object.entries(part.staticAttributes || {})) {
    assertions.push(`        assertTrue(html.contains("${name}=\\"${value}\\""))`)
  }
  return assertions.join('\n')
}

function generateCustomPartTests(className, customParts) {
  if (!customParts || customParts.length === 0) return ''

  let kotlin = ''
  for (const part of customParts) {
    const funcName = `custom_part_${part.name.toLowerCase()}_renders_${part.element.toLowerCase()}`
    const tag = part.element.toLowerCase()
    const receiver = part.receiver || 'FlowContent'
    
    // Determine wrapping context based on receiver type
    const wrapperTag = receiver === 'FlowContent' ? 'div' : receiver.toLowerCase()
    const wrapperFn = htmlTagFnFor(wrapperTag)

    kotlin += `
    @Test
    fun ${funcName}() {
        val html = createHTML(prettyPrint = false).${wrapperFn} {
            daisy${className}${part.name} {
            }
        }
${customPartAssertions(part, tag)}
    }
`
  }
  return kotlin
}

function htmlTagFnFor(tag) {
  const exceptions = { fieldset: 'fieldSet', textarea: 'textArea' }
  return exceptions[tag] ?? tag
}

function generateKotlinTest(componentName, testCases, frontmatter, config) {
  const className = toClassName(componentName)
  const { allowedClasses, classToParam, paramToGeneratedClass, componentClass } = buildClassMappings(frontmatter, componentName)
  const customParts = config?.customParts?.[componentName] || []
  const hasCustomParts = customParts.length > 0
  
  const extraImports = new Set()
  if (hasCustomParts) {
    extraImports.add('import kotlin.test.assertTrue')
    for (const part of customParts) {
      const receiver = part.receiver || 'FlowContent'
      if (receiver !== 'FlowContent') {
        extraImports.add(`import kotlinx.html.${htmlTagFnFor(receiver.toLowerCase())}`)
      }
    }
  }
  const extraImportLines = [...extraImports].sort().join('\n')
  
  let kotlin = `package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals${extraImportLines ? '\n' + extraImportLines : ''}

class ${className}Test {
`
  
  const usedNames = new Set()
  
  for (const tc of testCases) {
    let funcName = toTestFunctionName(tc.name)
    
    let uniqueName = funcName
    let counter = 2
    while (usedNames.has(uniqueName)) {
      uniqueName = `${funcName}_${counter}`
      counter++
    }
    usedNames.add(uniqueName)
    
    const allClasses = extractDaisyClasses(tc.html)
    const containerClasses = filterContainerClasses(allClasses, allowedClasses)
    const params = mapClassesToParams(containerClasses, classToParam)
    
    const paramStr = Object.entries(params)
      .filter(([k, v]) => v === true)
      .map(([k, v]) => `${k} = true`)
      .join(', ')
    
    const generatedClasses = [componentClass]
    for (const c of containerClasses) {
      if (c !== componentClass) {
        if (c.startsWith(`${componentClass}-`)) {
          generatedClasses.push(c)
        } else {
          generatedClasses.push(`${componentClass}-${c}`)
        }
      }
    }
    const expectedClasses = generatedClasses.sort().join(' ')
    
    kotlin += `
    @Test
    fun ${uniqueName}() {
        val html = createHTML(prettyPrint = false).div {
            daisy${className}(${paramStr}) {
            }
        }
        val expectedClasses = "${expectedClasses}"
        val actualClasses = html.substringAfter("class=\\"").substringBefore("\\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for ${tc.name}")
    }
`
  }
  
  kotlin += generateCustomPartTests(className, customParts)
  
  kotlin += `}
`
  
  return kotlin
}

function generateForComponent(componentName, config) {
  const pageFile = path.join(DOCS_DIR, componentName, '+page.md')
  
  if (!fs.existsSync(pageFile)) {
    return { success: false, error: 'File not found' }
  }
  
  const frontmatter = readComponentFrontmatter(componentName)
  if (!frontmatter) {
    return { success: false, error: 'No frontmatter' }
  }
  
  const content = fs.readFileSync(pageFile, 'utf8')
  const testCases = parseTestCases(content)
  
  if (testCases.length === 0) {
    return { success: false, error: 'No test cases' }
  }
  
  const kotlin = generateKotlinTest(componentName, testCases, frontmatter, config)
  const className = toClassName(componentName)
  const outFile = path.join(OUTPUT_DIR, `${className}Test.kt`)
  
  fs.writeFileSync(outFile, kotlin)
  return { success: true, testCount: testCases.length }
}

// Exhaustive branch-coverage tests: parse each generated component source and
// drive every branch of every function with assertions on the rendered HTML.

// Where the generated component sources are READ BACK from, to drive every branch
// of every function. Gradle passes --components-dir so lib/build.gradle.kts stays
// the single source of that path; the default only serves a bare `node` run.
//
// This used to be a second hardcoded copy of the path, and when the real one moved
// it silently produced no coverage tests at all — see generateAllCoverage below.
const GENERATED_MAIN_DIR = parseArg(
  'components-dir',
  path.resolve(import.meta.dirname, '../../lib/generated/main/kotlin/io/github/ollin/kdaisyui/components'),
)

// Enum types from kotlinx.html (not declared in the source): arms can't be
// enumerated, so the `!= null` branch is driven once with a known value.
const EXTERNAL_ENUM_VALUES = { ButtonType: 'ButtonType.button' }
const EXTERNAL_ENUM_IMPORTS = { ButtonType: 'import kotlinx.html.ButtonType' }

function lowerFirst(s) {
  return s.charAt(0).toLowerCase() + s.slice(1)
}

/** Walk from an opening delimiter to its matching close, respecting nesting. */
function matchDelimiter(str, openIdx, open, close) {
  let depth = 0
  for (let i = openIdx; i < str.length; i++) {
    if (str[i] === open) depth++
    else if (str[i] === close && --depth === 0) return i
  }
  throw new Error(`Unbalanced ${open} from index ${openIdx}`)
}

/** Map enumTypeName -> [{ entry, css }] for class-mapping enums in the file. */
function parseEnumDefinitions(content) {
  const enums = {}
  const re = /enum class (\w+)\(internal val className: String\)\s*\{([\s\S]*?)\n\}/g
  let m
  while ((m = re.exec(content)) !== null) {
    const entries = []
    const entryRe = /^\s*(\w+)\("([^"]+)"\),?\s*$/gm
    let em
    while ((em = entryRe.exec(m[2])) !== null) {
      entries.push({ entry: em[1], css: em[2] })
    }
    enums[m[1]] = entries
  }
  return enums
}

/** Extract every generated `fun <Receiver>.daisy<Name>(...) { ... }` block. */
function findFunctions(content) {
  const funcs = []
  const headerRe = /fun\s+(\w+)\.daisy(\w+)\s*\(/g
  let m
  while ((m = headerRe.exec(content)) !== null) {
    const parenStart = headerRe.lastIndex - 1
    const parenEnd = matchDelimiter(content, parenStart, '(', ')')
    const braceStart = content.indexOf('{', parenEnd)
    const braceEnd = matchDelimiter(content, braceStart, '{', '}')
    funcs.push({
      receiver: m[1],
      name: m[2],
      paramBlock: content.slice(parenStart + 1, parenEnd),
      body: content.slice(braceStart + 1, braceEnd),
    })
    headerRe.lastIndex = braceEnd
  }
  return funcs
}

/** Split a param block on top-level commas (parens protect lambda types). */
function splitParams(block) {
  const parts = []
  let depth = 0
  let last = 0
  for (let i = 0; i < block.length; i++) {
    const ch = block[i]
    if (ch === '(') depth++
    else if (ch === ')') depth--
    else if (ch === ',' && depth === 0) {
      parts.push(block.slice(last, i))
      last = i + 1
    }
  }
  const tail = block.slice(last)
  if (tail.trim()) parts.push(tail)
  return parts.map((p) => p.trim()).filter(Boolean)
}

const PARAM_KIND_RULES = [
  [(c) => c.baseType === 'Boolean', 'boolean'],
  [(c) => c.name === 'id', 'id'],
  [(c) => c.name === 'extraClasses', 'extraClasses'],
  [(c) => c.name === 'attrs', 'attrs'],
  [(c) => c.name === 'content', (c) => (c.nullable ? 'contentOptional' : 'contentRequired')],
  [(c) => c.name === 'text' && c.baseType === 'String', 'text'],
  [(c) => c.nullable && c.baseType === 'String', 'nullableString'],
  [(c) => c.nullable && c.enums[c.baseType], 'enumClass'],
  [(c) => c.nullable && EXTERNAL_ENUM_VALUES[c.baseType], 'enumExternal'],
  [(c) => !c.nullable && c.hasDefault, 'presetNonNull'],
]

function paramKind(ctx) {
  for (const [matches, kind] of PARAM_KIND_RULES) {
    if (matches(ctx)) return typeof kind === 'function' ? kind(ctx) : kind
  }
  return 'other'
}

function classifyParam(raw, enums) {
  const colon = raw.indexOf(':')
  const name = raw.slice(0, colon).trim()
  const rest = raw.slice(colon + 1).trim()
  const eq = rest.indexOf('=')
  const type = (eq >= 0 ? rest.slice(0, eq) : rest).trim()
  const nullable = type.endsWith('?')
  const baseType = (nullable ? type.slice(0, -1) : type).trim()
  const kind = paramKind({ name, baseType, nullable, hasDefault: eq >= 0, enums })
  return { name, type, baseType, nullable, hasDefault: eq >= 0, kind, enumEntries: kind === 'enumClass' ? enums[baseType] : null }
}

/** The single unguarded `addClassNames("...")` that names this element. */
function parseBaseClass(body) {
  const m = body.match(/^\s*addClassNames\("([^"]+)"\)\s*$/m)
  return m ? m[1] : null
}

/** CSS class(es) a boolean modifier adds via `if (param) addClassNames("...")`. */
function boolClassesFor(body, param) {
  const line = body.match(new RegExp(`^\\s*if \\(${param}\\)(.*)$`, 'm'))
  if (!line) return []
  const css = []
  const re = /addClassNames\("([^"]+)"\)/g
  let m
  while ((m = re.exec(line[1])) !== null) css.push(m[1])
  return css
}

function sortedClasses(arr) {
  return [...new Set(arr.filter(Boolean))].sort().join(' ')
}

const ACTUAL_CLASSES =
  'val actualClasses = html.substringAfter("class=\\"").substringBefore("\\"").split(" ").sorted().joinToString(" ")'

function renderTest(funcName, wrapperFn, callArgs, asserts) {
  const argStr = callArgs.length ? `\n${callArgs.map((a) => `                ${a},`).join('\n')}\n            ` : ''
  return `
    @Test
    fun ${funcName}() {
        val html = createHTML(prettyPrint = false).${wrapperFn} {
            daisy${''}REPLACE(${argStr})
        }
${asserts.map((a) => `        ${a}`).join('\n')}
    }
`
}

function coverageContext(fn, enums) {
  const params = splitParams(fn.paramBlock).map((raw) => classifyParam(raw, enums))
  return {
    params,
    body: fn.body,
    base: parseBaseClass(fn.body) || '',
    wrapperFn: fn.receiver === 'FlowContent' ? 'div' : htmlTagFnFor(fn.receiver.toLowerCase()),
    fnBase: lowerFirst(fn.name),
    daisyName: fn.name,
    required: params.find((p) => p.kind === 'contentRequired'),
    hasContent: params.some((p) => p.kind === 'contentRequired' || p.kind === 'contentOptional'),
    textParam: params.find((p) => p.kind === 'text'),
  }
}

function wrapTest(ctx, tname, args, asserts) {
  return renderTest(tname, ctx.wrapperFn, args, asserts).replace(`daisyREPLACE`, `daisy${ctx.daisyName}`)
}

function defaultsTest(ctx) {
  const args = ctx.required ? ['content = { }'] : []
  const asserts = ctx.base
    ? [ACTUAL_CLASSES, `assertEquals("${ctx.base}", actualClasses, "${ctx.daisyName} defaults")`]
    : [`assertTrue(!html.contains("class=\\""), "${ctx.daisyName} defaults emits no class")`]
  return wrapTest(ctx, `${ctx.fnBase}_defaults`, args, asserts)
}

function allFlagsArgs(ctx, boolCss) {
  const args = ['id = htmlId("x-cov-id")']
  for (const b of ctx.params.filter((p) => p.kind === 'boolean')) {
    args.push(`${b.name} = true`)
    boolCss.push(...boolClassesFor(ctx.body, b.name))
  }
  for (const s of ctx.params.filter((p) => p.kind === 'nullableString')) args.push(`${s.name} = "x"`)
  for (const e of ctx.params.filter((p) => p.kind === 'enumExternal')) args.push(`${e.name} = ${EXTERNAL_ENUM_VALUES[e.baseType]}`)
  args.push('extraClasses = "zz-extra"', 'attrs = { attributes["data-attrs"] = "yes" }')
  if (ctx.hasContent) args.push('content = { attributes["data-content"] = "yes" }')
  return args
}

function allFlagsAsserts(ctx, boolCss) {
  const asserts = [
    ACTUAL_CLASSES,
    `assertEquals("${sortedClasses([ctx.base, ...boolCss, 'zz-extra'])}", actualClasses, "${ctx.daisyName} all flags")`,
    `assertTrue(html.contains("id=\\"x-cov-id\\""), "${ctx.daisyName} id")`,
    `assertTrue(html.contains("data-attrs=\\"yes\\""), "${ctx.daisyName} attrs")`,
  ]
  if (ctx.hasContent) asserts.push(`assertTrue(html.contains("data-content=\\"yes\\""), "${ctx.daisyName} content")`)
  for (const s of ctx.params.filter((p) => p.kind === 'nullableString')) {
    asserts.push(`assertTrue(html.contains("${s.name}=\\"x\\""), "${ctx.daisyName} ${s.name}")`)
  }
  return asserts
}

function allFlagsTest(ctx) {
  const boolCss = []
  const args = allFlagsArgs(ctx, boolCss)
  return wrapTest(ctx, `${ctx.fnBase}_all_flags`, args, allFlagsAsserts(ctx, boolCss))
}

function enumArmTests(ctx) {
  let tests = ''
  for (const e of ctx.params.filter((p) => p.kind === 'enumClass')) {
    for (const { entry, css } of e.enumEntries) {
      const args = ctx.required ? [`${e.name} = ${e.baseType}.${entry}`, 'content = { }'] : [`${e.name} = ${e.baseType}.${entry}`]
      const asserts = [ACTUAL_CLASSES, `assertEquals("${sortedClasses([ctx.base, css])}", actualClasses, "${ctx.daisyName} ${e.name} ${entry}")`]
      tests += wrapTest(ctx, `${ctx.fnBase}_${e.name}_${entry.toLowerCase()}`, args, asserts)
    }
  }
  return tests
}

function textArmTest(ctx) {
  if (!ctx.textParam) return ''
  const asserts = [
    ACTUAL_CLASSES,
    `assertEquals("${ctx.base}", actualClasses, "${ctx.daisyName} text")`,
    `assertTrue(html.contains("txtmark"), "${ctx.daisyName} text content")`,
  ]
  return wrapTest(ctx, `${ctx.fnBase}_text`, ['text = "txtmark"'], asserts)
}

function buildCoverageTests(fn, enums) {
  const ctx = coverageContext(fn, enums)
  return defaultsTest(ctx) + allFlagsTest(ctx) + enumArmTests(ctx) + textArmTest(ctx)
}

function generateCoverageForFile(fileName) {
  const filePath = path.join(GENERATED_MAIN_DIR, fileName)
  const content = fs.readFileSync(filePath, 'utf8')
  const className = fileName.replace(/\.kt$/, '')
  const enums = parseEnumDefinitions(content)
  const funcs = findFunctions(content)
  if (funcs.length === 0) return { success: false, error: 'No functions' }

  const imports = new Set([
    'import io.github.ollin.kdaisyui.core.htmlId',
    'import kotlinx.html.div',
    'import kotlinx.html.stream.createHTML',
    'import kotlin.test.Test',
    'import kotlin.test.assertEquals',
    'import kotlin.test.assertTrue',
  ])
  for (const fn of funcs) {
    if (fn.receiver !== 'FlowContent') imports.add(`import kotlinx.html.${htmlTagFnFor(fn.receiver.toLowerCase())}`)
    for (const raw of splitParams(fn.paramBlock)) {
      const p = classifyParam(raw, enums)
      if (p.kind === 'enumExternal' && EXTERNAL_ENUM_IMPORTS[p.baseType]) imports.add(EXTERNAL_ENUM_IMPORTS[p.baseType])
    }
  }

  let body = ''
  for (const fn of funcs) body += buildCoverageTests(fn, enums)

  const kotlin = `package io.github.ollin.kdaisyui.components

${[...imports].sort().join('\n')}

class ${className}CoverageTest {
${body}}
`
  fs.writeFileSync(path.join(OUTPUT_DIR, `${className}CoverageTest.kt`), kotlin)
  return { success: true, funcCount: funcs.length }
}

function generateAllCoverage() {
  // FAIL, never skip. This used to warn and return, so a wrong path produced zero
  // coverage tests while the build stayed green and the other generators kept the
  // test count looking plausible. That cost 8% line and 9% branch coverage without
  // turning anything red. A missing input is a broken build, not a warning.
  if (!fs.existsSync(GENERATED_MAIN_DIR)) {
    throw new Error(
      `Generated component sources not found at ${GENERATED_MAIN_DIR}. ` +
      `Coverage tests are read back from them, so this cannot be skipped. ` +
      `Pass --components-dir=<path>, or check generatedMainDir in lib/build.gradle.kts.`
    )
  }
  const files = fs.readdirSync(GENERATED_MAIN_DIR).filter((f) => f.endsWith('.kt')).sort()
  if (files.length === 0) {
    throw new Error(`No .kt files in ${GENERATED_MAIN_DIR}; generateComponents must run first.`)
  }
  let generated = 0
  for (const file of files) {
    const result = generateCoverageForFile(file)
    if (result.success) generated++
    else console.log(`  ⊘ coverage ${file}: ${result.error}`)
  }
  console.log(`Generated coverage tests for ${generated} component files`)
}

function main() {
  const args = process.argv.slice(2)
  const mode = args[0]
  const config = loadConfig()
  
  if (mode === 'all') {
    console.log('Generating tests for all components...\n')
    
    const componentDirs = getAllComponentDirs()
    let generated = 0
    let skipped = 0
    
    for (const componentName of componentDirs) {
      if (config.skip?.includes(componentName)) {
        console.log(`  ⊘ ${componentName}: Skipped (alias)`)
        skipped++
        continue
      }
      
      const result = generateForComponent(componentName, config)
      
      if (result.success) {
        console.log(`  ✓ ${componentName}: ${result.testCount} tests`)
        generated++
      } else {
        console.log(`  ⊘ ${componentName}: ${result.error}`)
        skipped++
      }
    }
    
    console.log(`\nGenerated tests for ${generated} components, skipped ${skipped}`)
    generateAllCoverage()
  } else if (mode) {
    if (config.skip?.includes(mode)) {
      console.error(`Error: ${mode} is skipped (alias)`)
      process.exit(1)
    }
    
    const result = generateForComponent(mode, config)
    
    if (result.success) {
      console.log(`Generated ${result.testCount} tests for ${mode}`)
    } else {
      console.error(`Error: ${result.error}`)
      process.exit(1)
    }
  } else {
    console.log('Usage: node test-generator.js <component-name|all>')
    console.log('Examples:')
    console.log('  node test-generator.js dropdown')
    console.log('  node test-generator.js all')
    process.exit(1)
  }
}

main()
