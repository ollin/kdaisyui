# kdaisyUI Project Knowledge Base

**Generated:** 2026-03-29
**Stack:** Kotlin 2.3.x + Gradle 9.x + kotlinx.html + DaisyUI

## OVERVIEW

Type-safe DaisyUI component DSL for Kotlin server-rendered HTML. Wraps Tailwind CSS classes into compile-time-checked Kotlin functions.

## STRUCTURE

```
kdaisyUI/
├── lib/                    # Core DSL library (publishable)
│   └── src/main/kotlin/kdaisyui/
│       ├── components/     # 22 DaisyUI component wrappers
│       └── core/           # ClassNames utility
├── example-app/            # Ktor demo server (port 8080)
├── e2e-tests/              # Playwright E2E tests
├── buildSrc/               # Gradle convention plugins
└── docs/                   # Diátaxis documentation
```

## WHERE TO LOOK

| Task | Location |
|------|----------|
| Add new component | `lib/src/main/kotlin/kdaisyui/components/` |
| Fix component bug | `lib/src/main/kotlin/kdaisyui/components/<Component>.kt` |
| Add tests | `lib/src/test/kotlin/kdaisyui/components/` |
| Demo app routes | `example-app/src/main/kotlin/kdaisyui/example/` |
| E2E tests | `e2e-tests/tests/` |
| Build config | `buildSrc/`, `gradle.properties`, `settings.gradle.kts` |
| Version pins | `gradle.properties`, `.tool-versions` |

## CONVENTIONS

- **Component pattern**: Each component = one file with `FlowContent.daisyXxx()` extension function
- **Enum variants**: `XxxVariant`, `XxxSize` enums with `internal val className`
- **Testing**: KotlinTest with `createHTML().div { }` assertions
- **Build**: Gradle Kotlin DSL, convention plugin in `buildSrc/`
- **Code style**: `kotlin.code.style=official`

## ANTI-PATTERNS

- No `DO NOT`/`NEVER` markers found in codebase
- Avoid hardcoding CSS classes — use enum variants
- Don't skip E2E tests for UI changes

## COMMANDS

Prefer `just` recipes — they are the canonical entry points for all tasks:

```bash
just test        # Unit tests (lib)
just e2e         # E2E tests — Playwright starts/stops the Ktor server automatically
just test-all    # Unit tests + E2E (full suite)
just dev         # Demo server → http://localhost:8080
just build       # Build all Gradle modules
just clean       # Remove all build artifacts
```

Raw Gradle commands (what just recipes call under the hood):

```bash
./gradlew :lib:test              # Unit tests
./gradlew :example-app:run       # Demo server (localhost:8080)
cd e2e-tests && npm test         # E2E tests (Playwright config handles server lifecycle)
```

## NOTES

- No published artifacts yet — use composite builds
- Kotlin version split: `gradle.properties` (2.3.10) vs `libs.versions.toml` (2.3.20) — reconcile if issues
- Missing root `build.gradle.kts` — config in subprojects only


## IntelliJ MCP Server - AVAILABLE AND PREFERRED

**CONTEXT**: This project runs inside IntelliJ IDEA. The `mcp_intellij-server_*` tools are available and MUST be preferred over raw shell commands where applicable.

### Priority Rules

| Task | USE THIS | NOT THIS |
|------|----------|----------|
| Build project | `mcp_intellij-server_build_project` | ❌ `./gradlew build` via Bash |
| Search in files | `mcp_intellij-server_search_in_files_by_text` | ❌ `grep -r` |
| Search by symbol | `mcp_intellij-server_search_symbol` | ❌ `grep` for class names |
| File problems / linting | `mcp_intellij-server_get_file_problems` | ❌ Only compiler output |
| Rename refactoring | `mcp_intellij-server_rename_refactoring` | ❌ Search & replace |
| Run tests | `mcp_intellij-server_execute_run_configuration` | ❌ `./gradlew test` |
| Browse directory | `mcp_intellij-server_list_directory_tree` | ❌ `ls` / `find` |
| Run terminal cmd | `mcp_intellij-server_execute_terminal_command` | Only when no IntelliJ tool exists |

### Key IntelliJ Tools for this Project

- **`build_project`** — Structured Kotlin compiler errors (better than raw Gradle output)
- **`get_file_problems`** — IntelliJ inspections (catches more than just compiler errors)
- **`search_symbol`** — Find Kotlin classes/functions by name across project
- **`search_in_files_by_regex`** — Regex search with IntelliJ's fast index
- **`rename_refactoring`** — LSP-aware rename, updates all references
- **`get_run_configurations`** + **`execute_run_configuration`** — Run `:lib:test`, `:example-app:run` etc.

---

## CodeSeeker MCP Tools - MANDATORY FOR CODE DISCOVERY

**CRITICAL**: This project has CodeSeeker MCP tools available. You MUST use them as your PRIMARY method for **semantic** code discovery, NOT grep/glob.

### Auto-Initialization Check (DO THIS FIRST)

**BEFORE any code search**, verify the project is indexed:
1. Call `mcp_codeseeker_codeseeker` with `action: "index"`, `op: "status"` to see indexed projects
2. If this project is NOT listed, call with `action: "index"`, `op: "init"`, `path: "/home/ollin/dev/src/github.com/ollin/kdaisyUI"` first
3. If tools return "Not connected", the MCP server may need restart

### When to Use CodeSeeker (DEFAULT)

**ALWAYS use CodeSeeker for these queries:**
- "Where is X handled?" → `search({query: "X handling logic"})`
- "Find the auth/login/validation code" → `search({query: "authentication"})`
- "How does Y work?" → `search({query: "Y implementation", read: true})`
- "What calls/imports Z?" → `analyze({action: "dependencies", filepath: "path/to/Z"})`
- "Show me the error handling" → `search({query: "error handling patterns", read: true})`

| Task | MUST Use | NOT This |
|------|----------|----------|
| Find code by meaning | `search({query: "authentication logic"})` | ❌ `grep -r "auth"` |
| Search + read files | `search({query: "error handling", read: true})` | ❌ `grep` then `cat` |
| Show dependencies | `analyze({action: "dependencies", filepath: "..."})` | ❌ Manual file reading |
| Find patterns | `analyze({action: "standards"})` | ❌ Searching manually |
| Understand a file | `search({filepath: "..."})` | ❌ Just Read alone |

### When to Use grep/glob (EXCEPTIONS ONLY)

Only fall back to grep/glob when:
- Searching for **exact literal strings** (UUIDs, specific error codes, magic numbers)
- Using **regex patterns** that semantic search can't handle
- You **already know the exact file path**

### Why CodeSeeker is Better

```
❌ grep -r "error handling" src/
   → Only finds literal text "error handling"

✅ search("how errors are handled")
   → Finds: try-catch blocks, .catch() callbacks, error responses,
     validation errors, custom Error classes - even if they don't
     contain the words "error handling"
```

### Available MCP Tools (3 consolidated)

| Tool | Purpose | When to Use |
|------|---------|-------------|
| `search({query})` | Semantic search | First choice for any "find X" query |
| `search({query, read: true})` | Search + read combined | When you need file contents |
| `search({filepath})` | File + related code | Reading a file for the first time |
| `analyze({action: "dependencies", filepath})` | Dependency graph | "What uses this?" |
| `analyze({action: "standards"})` | Project patterns | Before writing new code |
| `analyze({action: "duplicates"})` | Find duplicate code | Code cleanup |
| `analyze({action: "dead_code"})` | Find unused code | Architecture review |
| `index({action: "init", path})` | Index a project | If project not indexed |
| `index({action: "sync", changes})` | Update index | After editing files |
| `index({action: "status"})` | Show indexed projects | Check if project is indexed |

### Keep Index Updated

After using Edit/Write tools, call:
```
index({action: "sync", changes: [{type: "modified", path: "path/to/file"}]})
```

## Claude Code Best Practices (from 2000+ hours of expert usage)

### Subagent Strategy for Complex Tasks
- For multi-step or complex tasks, spawn subagents using the **main model** (not cheaper/smaller models) instead of cramming everything into one context
- Pattern: "Orchestrator coordinates + focused subagents execute" >> "Single massive context"
- Use subagents MORE than you think necessary, especially for large codebases
- Each subagent gets fresh, focused context = better quality output

### Context Hygiene - Prevent "Lost in the Middle"
- Quality degrades as context grows - the "lost in the middle" problem is real
- Use **double-escape (Esc Esc)** to time travel when context gets polluted with failed attempts
- Compact strategically and intentionally, not automatically
- When output quality drops, consider starting fresh rather than adding more context

### Error Attribution Mindset
- Issues in AI-generated code trace back to **prompting or context engineering**
- When something fails, ask "what context was missing?" not "the AI is broken"
- Log failures mentally: prompt → context → outcome. Patterns will emerge.
- Better input = better output. Always.

