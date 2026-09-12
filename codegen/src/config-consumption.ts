/**
 * Which component-keyed config entries a generation run actually read.
 *
 * A mis-keyed entry is indistinguishable from an absent one at run time: the lookup returns
 * `undefined`, the caller's `?? []` swallows it, and the component is generated as though
 * nothing had been configured. Nothing fails, and the class-based safety net cannot help —
 * a knob that changes no CSS class is invisible to both the generated tests and the drift job.
 *
 * That is not hypothetical. `noContent` held `file-input` and `theme-controller` while the
 * lookup asked for `fileinput` and `themecontroller`, so `daisyFileInput` and
 * `daisyThemeController` demanded a content lambda on a void `<input>` for as long as those
 * entries existed. `subComponentElements` sat unread for months for a related reason.
 *
 * A key-EXISTENCE check would not have caught either: `file-input` is a real DaisyUI directory.
 * The entry is not a typo, it is unread, and only consumption distinguishes the two.
 */

/** The config sections whose keys are component identifiers, and how each is addressed. */
export type SectionKeying = 'component' | 'part' | 'directory'

export interface ConfigSection {
  readonly name: string
  readonly keying: SectionKeying
  /** A list of names rather than an object keyed by them. */
  readonly isList?: boolean
}

/**
 * Every section a run can consume, with how it is keyed.
 *
 * Two conventions coexist and that is the underlying defect — `skip`, `componentElements` and
 * `docSummaries` are addressed by DaisyUI's directory name, the rest by the lower-cased
 * PascalCase name. Unifying them is deliberately out of scope; this guard makes deferring it
 * safe, because a key nobody reads now fails the run.
 */
export const CONFIG_SECTIONS: readonly ConfigSection[] = [
  { name: 'extras', keying: 'component' },
  { name: 'customParts', keying: 'component' },
  { name: 'roles', keying: 'component' },
  { name: 'inputTypes', keying: 'component' },
  { name: 'componentAttributes', keying: 'component' },
  { name: 'additionalBooleans', keying: 'component' },
  { name: 'textParams', keying: 'component', isList: true },
  { name: 'skip', keying: 'directory', isList: true },
  { name: 'componentElements', keying: 'directory' },
  { name: 'docSummaries', keying: 'directory' },
  { name: 'elementCrossCheckExceptions', keying: 'directory' },
  { name: 'subComponentElements', keying: 'part' },
]

/** What a run saw, so the guard can compare it against what the config offers. */
export interface ConsumedKeys {
  /** Lower-cased PascalCase names, e.g. `fileinput` — how most sections are addressed. */
  readonly componentKeys: ReadonlySet<string>
  /** DaisyUI directory names, e.g. `file-input`. */
  readonly directoryKeys: ReadonlySet<string>
  /** Part class names, e.g. `card-title`. */
  readonly partKeys: ReadonlySet<string>
}

export interface UnreadEntry {
  readonly section: string
  readonly key: string
  readonly message: string
}

/**
 * Collects the identifiers a run touched.
 *
 * A mutable collector rather than three sets passed around, because the caller's job is to
 * generate components and the bookkeeping should cost it one object and three calls.
 */
export class ConsumedKeyCollector {
  private readonly components = new Set<string>()
  private readonly directories = new Set<string>()
  private readonly parts = new Set<string>()

  /** A DaisyUI directory name, recorded for EVERY component, including skipped ones. */
  directory(name: string): void {
    this.directories.add(name)
  }

  /** The lower-cased PascalCase name most sections are addressed by, plus the component's parts. */
  component(pascalName: string, partClasses: readonly string[]): void {
    this.components.add(pascalName.toLowerCase())
    for (const part of partClasses) this.parts.add(part)
  }

  keys(): ConsumedKeys {
    return { componentKeys: this.components, directoryKeys: this.directories, partKeys: this.parts }
  }
}

function keysFor(keying: SectionKeying, consumed: ConsumedKeys): ReadonlySet<string> {
  if (keying === 'directory') return consumed.directoryKeys
  if (keying === 'part') return consumed.partKeys
  return consumed.componentKeys
}

function entryKeys(config, section: ConfigSection): string[] {
  const value = config?.[section.name]
  if (value === undefined || value === null) return []
  return section.isList ? [...value] : Object.keys(value)
}

/**
 * A `textParams` entry may legitimately be a PART class rather than a component — `card-title`
 * takes inline text. So that one section is satisfied by either.
 */
function isReachable(section: ConfigSection, key: string, consumed: ConsumedKeys): boolean {
  if (keysFor(section.keying, consumed).has(key)) return true
  return section.name === 'textParams' && consumed.partKeys.has(key)
}

/** Every component-keyed config entry that no lookup touched during the run. */
export function findUnreadEntries(config, consumed: ConsumedKeys): UnreadEntry[] {
  const unread: UnreadEntry[] = []

  for (const section of CONFIG_SECTIONS) {
    for (const key of entryKeys(config, section)) {
      if (isReachable(section, key, consumed)) continue
      unread.push({
        section: section.name,
        key,
        message:
          `${section.name}."${key}" was never read. Sections keyed by ${section.keying} name ` +
          `expect one of the run's ${section.keying} identifiers; this entry matches none, so ` +
          `it has no effect. Correct the key or delete the entry.`,
      })
    }
  }

  return unread
}

/** The message the generator dies with. One line per entry, so none of them is buried. */
export function describeUnreadEntries(unread: readonly UnreadEntry[]): string {
  return [
    `Unread configuration (${unread.length}):`,
    ...unread.map(entry => `  ✗ ${entry.message}`),
  ].join('\n')
}
