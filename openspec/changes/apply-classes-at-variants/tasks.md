# Tasks

Ordered by uncertainty before dependency. Section 1 is measurement, and its results can rewrite
section 2 before a line of the API is designed.

Estimates frozen 2026-09-13 before starting, per `estimate-calibration`. Block 2 is priced high on
purpose: it carries **three** open decisions and no precedent in this repository, which the
previous run's finding says is what actually costs time.

## 1. Measure before designing — 0.2 h

- [ ] 1.1 `. d` Which variants appear in real use? Count, across `example-app`, `e2e-tests`,
  `docs` and DaisyUI's own documented examples: which prefixes occur, how often, and **how often
  two are stacked on one class**. A zero stacking count refutes the stacking assumption and
  simplifies section 2.
- [ ] 1.2 `. d` Is the variant set closeable? List every distinct prefix DaisyUI's own examples
  use. If they are all breakpoints, states and DaisyUI's two, the type can be closed; if
  `group-*`, `peer-*` or arbitrary values appear, it cannot, and the design must carry an escape
  hatch rather than pretend.
- [ ] 1.3 `. d` Triage the 19 classes no typed parameter reaches. Separate the **defects**
  (`step-*` missing as a part, `tab-active`/`tab-disabled` belonging to #342) from the genuinely
  unparameterised (`join-item`, `indicator-item`, `avatar-group`, `floating-label`, `list-row`)
  from the non-classes (#345). Record which group each falls in; only the middle group is this
  change's business.

## 2. Design the variant type — 1.2 h

Three decisions, none with a precedent here. Each gets written down with its rejected
alternatives, because the next person will otherwise re-open it.

- [ ] 2.1 `. d` **Decide the call shape.** `ButtonSize.Lg at Breakpoint.Lg`, an infix on the enum,
  a wrapper function, or something else. Constraint: it must read well at a call site that already
  names a parameter — `size = ButtonSize.Lg at Breakpoint.Lg` — and must not require every generated
  enum to gain a method.
- [ ] 2.2 `. d` **Decide the variant set**, informed by 1.2. Closed enum, open value type, or a
  closed enum plus a documented escape hatch.
- [ ] 2.3 `. d` **Decide composition**, informed by 1.1. If stacking is supported: what forbids
  `Breakpoint.Md at Breakpoint.Lg`, which is meaningless, while allowing `Dark at Breakpoint.Md`?
  Type-level separation of breakpoints from states is the obvious answer and should be stated as a
  decision rather than assumed.
- [ ] 2.4 `^ r (internal)` Add the type with its tests, unreferenced by any generated code.

## 3. Carry it through the generator — 0.6 h

- [ ] 3.1 `. d` **Decide how a variant reaches a parameter.** Whether the generated `variant`/
  `size` parameters accept the new type, whether the booleans do, or whether a separate parameter
  carries them. One decision, and it determines the whole of 3.2.
- [ ] 3.2 `^ F` Emit it. Test first, against `daisyButton(size = ButtonSize.Lg at Breakpoint.Lg)`
  producing `class="btn lg:btn-lg"`.
- [ ] 3.3 `^ F` Regenerate; read the diff; update both API baselines with
  `:lib:updateComponentApi` and `:lib:updateKotlinAbi`, reading each.

## 4. The genuine parameter gaps — 0.4 h

- [ ] 4.1 `^ F` Add typed parameters for the classes 1.3 put in the middle group. Defects found
  there go to their issues, not into this change.

## 5. Gate and adoption — 0.4 h

- [ ] 5.1 Full green per `openspec/config.yaml` — repo-wide compile, complete suite including e2e,
  coverage, `analyze_change_set`, plus `:lib:checkComponentApi`.
- [ ] 5.2 `openspec validate --all --strict`.
- [ ] 5.3 `. d` **How to migrate** is not needed — this change is additive — but `README.md` gains
  a usage example, because an API nobody can find is not an improvement over a string.
- [ ] 5.4 Write the evaluation under `./tmp/` for Oliver to adopt.
