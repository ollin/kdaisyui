// Classification of DaisyUI CSS modifier suffixes into variants, sizes, booleans

const SIZE_TOKENS = new Set(['xs', 'sm', 'md', 'lg', 'xl'])

const VARIANT_TOKENS = new Set([
  'primary', 'secondary', 'accent', 'neutral',
  'info', 'success', 'warning', 'error',
  'ghost', 'link',
])

/**
 * Extract modifier class suffixes from a CSS file for a given component prefix.
 * Reads lines like `.btn-primary`, `.btn-outline {` and returns the suffix part.
 */
export function extractSuffixes(cssContent, prefix) {
  const regex = new RegExp(`\\.${prefix}-([a-z][a-z0-9-]*)(?=[\\s{,:(]|$)`, 'gm')
  const suffixes = new Set()
  let match
  while ((match = regex.exec(cssContent)) !== null) {
    suffixes.add(match[1])
  }
  return [...suffixes]
}

/**
 * Classify suffixes into { variants, sizes, booleans }.
 * Each array contains only the suffix string.
 * @param {string[]} suffixes
 * @param {string[]} excludeSuffixes - suffixes to exclude (e.g. sub-component base classes)
 * @param {string[]} additionalBooleans - extra booleans not in CSS
 */
export function classifySuffixes(suffixes, excludeSuffixes = [], additionalBooleans = []) {
  const excluded = new Set(excludeSuffixes)
  const variants = []
  const sizes = []
  const booleans = []

  for (const suffix of suffixes) {
    if (excluded.has(suffix)) continue
    if (SIZE_TOKENS.has(suffix)) {
      sizes.push(suffix)
    } else if (VARIANT_TOKENS.has(suffix)) {
      variants.push(suffix)
    } else {
      booleans.push(suffix)
    }
  }

  // Add explicit additionalBooleans if not already present
  for (const b of additionalBooleans) {
    if (!booleans.includes(b) && !SIZE_TOKENS.has(b) && !VARIANT_TOKENS.has(b)) {
      booleans.push(b)
    }
  }

  // Sort: sizes in xs/sm/md/lg/xl order, variants alphabetically, booleans alphabetically
  const sizeOrder = ['xs', 'sm', 'md', 'lg', 'xl']
  sizes.sort((a, b) => sizeOrder.indexOf(a) - sizeOrder.indexOf(b))
  variants.sort()
  booleans.sort()

  return { variants, sizes, booleans }
}

/** Convert a hyphenated suffix to PascalCase enum entry name */
export function toPascalCase(suffix) {
  return suffix.split('-').map(p => p.charAt(0).toUpperCase() + p.slice(1)).join('')
}

/** Convert a hyphenated suffix to camelCase parameter name */
export function toCamelCase(suffix) {
  const pascal = toPascalCase(suffix)
  return pascal.charAt(0).toLowerCase() + pascal.slice(1)
}
