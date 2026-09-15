/*
 * The decision procedure, as it runs INSIDE the browser.
 *
 * Plain browser JavaScript on purpose, and the only file here that Node does not run: it is
 * loaded by the page `index-exclusivity.ts` writes, because deciding whether two CSS classes
 * conflict needs a cascade, a layout and a theme — none of which exist outside a browser.
 *
 * It is a committed file rather than a string inside the generator so that the rule which
 * decides the library's public API is reviewable in a diff.
 *
 * It writes the `a|b` and `component.category` key format that `measurement.ts` owns on the
 * Node side. Nothing here can import that module — this page is not bundled — so the two are
 * the only places allowed to know the format, and both say so.
 *
 * NEVER return a signature from this file to a caller. A signature is the full computed style
 * of every element in a subtree; 463 of them once cost 21 MB of an agent's context. The
 * verdicts are three words per pair and are the only thing worth carrying out.
 */
;(function () {
  const VERDICTS = ['exclusive', 'compose', 'same']

  /**
   * A signature short enough to compare and store.
   *
   * Two independent 32-bit accumulators plus the length, because one 32-bit hash over ~100 kB
   * of declarations per case would collide often enough to invent an `exclusive` verdict — the
   * expensive direction.
   */
  function digest(text) {
    let first = 0x811c9dc5 >>> 0
    let second = 0x27d4eb2f >>> 0
    for (let index = 0; index < text.length; index++) {
      const code = text.charCodeAt(index)
      first = Math.imul(first ^ code, 16777619) >>> 0
      second = Math.imul(second + code, 2654435761) >>> 0
    }
    return first.toString(16) + '-' + second.toString(16) + '-' + text.length
  }

  /** Every declaration the browser resolved for one element, including its two pseudo-elements. */
  function declarationsOf(element) {
    return [null, '::before', '::after'].map(function (pseudo) {
      const computed = getComputedStyle(element, pseudo)
      let text = ''
      for (let index = 0; index < computed.length; index++) {
        text += computed[index] + ':' + computed.getPropertyValue(computed[index]) + ';'
      }
      return text
    })
  }

  /**
   * The box of one element relative to the case root.
   *
   * Relative because a class can change only layout, while an absolute position would differ
   * for every case merely by being further down the page.
   */
  function boxOf(element, origin) {
    const box = element.getBoundingClientRect()
    return [box.x - origin.x, box.y - origin.y, box.width, box.height]
      .map(function (value) {
        return Math.round(value * 100) / 100
      })
      .join(',')
  }

  /**
   * Everything the browser resolved for one case, as one digest.
   *
   * The whole subtree, because a class often styles a child — `pin-rows` styles
   * `:where(thead tr)` and nothing on the component itself.
   */
  function signature(root) {
    const origin = root.getBoundingClientRect()
    const parts = []
    const walk = function (element) {
      parts.push.apply(parts, declarationsOf(element))
      parts.push(boxOf(element, origin))
      for (const child of element.children) walk(child)
    }
    walk(root)
    return digest(parts.join('|'))
  }

  /**
   * What the pair `left|right` established.
   *
   * The order of the checks matters: a combination that differs from both singles is
   * `compose` even when the two singles happen to look alike, because the combination reaching
   * new CSS is a positive observation and looking alike is not.
   */
  function verdictFor(cases, left, right) {
    const alone = cases[left]
    const other = cases[right]
    const together = cases[left + '|' + right]
    if (together !== alone && together !== other) return 'compose'
    if (alone === other) return 'same'
    return 'exclusive'
  }

  /** One signature per case, grouped by the group the case belongs to, plus the group's prefix. */
  function signaturesByGroup() {
    const signatures = {}
    for (const node of document.querySelectorAll('.probe-case')) {
      const group = node.dataset.group
      signatures[group] = signatures[group] || { prefix: node.dataset.prefix, cases: {} }
      signatures[group].cases[node.dataset.pair] = signature(node)
    }
    return signatures
  }

  /** The members of a group: every case key that is a single class. */
  function membersOf(cases) {
    return Object.keys(cases).filter(function (key) {
      return key !== '' && key.indexOf('|') < 0
    })
  }

  /**
   * Members whose single case equals the baseline — the class changed nothing in this example.
   *
   * Recorded beside the pair verdicts, not instead of them: the pairs are what was observed,
   * and an inert member's pairs read `exclusive` honestly. It is the AXIS derivation that must
   * leave an inert member out, because "exclusive with everything" is not membership of a clique.
   */
  function inertOf(cases) {
    const baseline = cases['']
    return membersOf(cases).filter(function (member) {
      return cases[member] === baseline
    })
  }

  /**
   * Whether a selector, or one of the nested rule's ancestors, names the class as a whole token.
   *
   * The dot is the boundary on the left: the CSSOM flattens `&.dropdown-left` under
   * `.dropdown-start` to `.dropdown-start.dropdown-left`, where the second class follows a
   * letter, not a space.
   */
  function selectorNames(selectors, className) {
    const token = new RegExp('\\.' + className + '(?![\\w-])')
    return selectors.some(function (selector) {
      return token.test(selector)
    })
  }

  /**
   * Every property name a stylesheet declares under the class ON ITS OWN: in any rule whose
   * selector chain names it and names no other member of its group — nested rules included,
   * since DaisyUI's CSS is nested throughout. `.dropdown-start.dropdown-left { --anchor-v }`
   * is what the combination declares, not what `dropdown-start` does.
   *
   * Read from the CSSOM rather than the source text, because what the browser parsed is what
   * applies. Custom properties come back as `--tt-trans`, ordinary ones as `transform`.
   */
  function declaredProperties(className, siblings) {
    const names = new Set()
    const namesASibling = function (selectors) {
      return siblings.some(function (sibling) {
        return selectorNames(selectors, sibling)
      })
    }
    const walk = function (rules, ancestors) {
      for (const rule of rules) {
        const own = rule.selectorText ? ancestors.concat([rule.selectorText]) : ancestors
        if (rule.style && selectorNames(own, className) && !namesASibling(own)) {
          for (let index = 0; index < rule.style.length; index++) names.add(rule.style[index])
        }
        if (rule.cssRules) walk(rule.cssRules, own)
      }
    }
    for (const sheet of document.styleSheets) walk(sheet.cssRules, [])
    return Array.from(names).sort()
  }

  /** `member -> [property, …]` for every member of the group. */
  function declaresOf(prefix, cases) {
    const declares = {}
    const classes = membersOf(cases).map(function (member) {
      return prefix + '-' + member
    })
    for (const className of classes) {
      const siblings = classes.filter(function (other) {
        return other !== className
      })
      declares[className.slice(prefix.length + 1)] = declaredProperties(className, siblings)
    }
    return declares
  }

  /** One group's verdicts, in the shape `exclusivity.json` stores them. */
  function verdictsOf(group) {
    const cases = group.cases
    const byVerdict = { exclusive: [], compose: [], same: [] }
    for (const key of Object.keys(cases)) {
      const members = key.split('|')
      if (members.length === 2) byVerdict[verdictFor(cases, members[0], members[1])].push(key)
    }

    const entry = {}
    for (const verdict of VERDICTS) {
      if (byVerdict[verdict].length > 0) entry[verdict] = byVerdict[verdict]
    }
    const inert = inertOf(cases)
    if (inert.length > 0) entry.inert = inert
    entry.declares = declaresOf(group.prefix, cases)
    return entry
  }

  /** The `groups` object of `codegen/exclusivity.json`, computed from the rendered page. */
  function measure() {
    const signatures = signaturesByGroup()
    const groups = {}
    for (const group of Object.keys(signatures).sort()) {
      const separator = group.indexOf('.')
      const component = group.slice(0, separator)
      groups[component] = groups[component] || {}
      groups[component][group.slice(separator + 1)] = verdictsOf(signatures[group])
    }
    return groups
  }

  /**
   * One `component.category pair=verdict` line per measured pair and one `member=inert` line
   * per inert member, for a whole component. `declares` is supporting data and stays out.
   */
  function linesOfComponent(component, categories) {
    const lines = []
    for (const category of Object.keys(categories)) {
      for (const verdict of VERDICTS) {
        for (const pair of categories[category][verdict] || []) {
          lines.push(component + '.' + category + ' ' + pair + '=' + verdict)
        }
      }
      for (const member of categories[category].inert || []) {
        lines.push(component + '.' + category + ' ' + member + '=inert')
      }
    }
    return lines
  }

  /**
   * A digest over the flattened verdicts, so a committed file can be checked against a fresh
   * run without carrying 310 lines back through a tool call.
   */
  function verdictDigest(groups) {
    const lines = Object.keys(groups).reduce(function (all, component) {
      return all.concat(linesOfComponent(component, groups[component]))
    }, [])
    lines.sort()
    return { pairs: lines.length, digest: digest(lines.join('\n')) }
  }

  /**
   * The complete `codegen/exclusivity.json`, ready to be written verbatim.
   *
   * The provenance is carried into the page by the generator rather than written here,
   * because it names the DaisyUI version, which only the Node side can read. Emitting the
   * WHOLE file is what lets the runner overwrite it wholesale — the property every other
   * generated artefact in this repository has.
   */
  function document_() {
    const provenance = JSON.parse(document.getElementById('kdaisyui-provenance').textContent)
    return { measurement: provenance, groups: measure() }
  }

  /** Called by the Playwright runner; also called once on load to fill the page. */
  window.kdaisyuiExclusivity = function () {
    const complete = document_()
    return {
      document: JSON.stringify(complete, null, 2) + '\n',
      check: verdictDigest(complete.groups),
    }
  }

  window.addEventListener('load', function () {
    const result = window.kdaisyuiExclusivity()
    document.getElementById('kdaisyui-summary').textContent =
      result.check.pairs + ' pairs, digest ' + result.check.digest
    document.getElementById('kdaisyui-verdicts').textContent = result.document
  })
})()
