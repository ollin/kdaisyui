# Credits and Acknowledgements

## DaisyUI

[DaisyUI](https://daisyui.com/) by [Pouya Saadeghi](https://github.com/saadeghi).

DaisyUI does something genuinely hard: it takes Tailwind CSS — a utility-first framework that produces verbose, hard-to-read class strings — and turns it into a semantic component system. Instead of `class="flex items-center justify-between px-4 py-2 rounded-lg bg-blue-600 text-white font-medium hover:bg-blue-700"`, you write `class="btn btn-primary"`. That is a meaningful abstraction, done right.

kdaisyUI exists because DaisyUI exists. The entire point of this library is to bring DaisyUI's semantic component model into Kotlin's type system — so that `btn-primary` becomes `ButtonVariant.Primary` and the compiler catches mistakes that CSS class strings cannot.

## Tailwind CSS

[Tailwind CSS](https://tailwindcss.com/) by Adam Wathan and the Tailwind Labs team. The utility-first foundation that DaisyUI builds on, and the layout system used throughout the example app.

## kotlinx.html

[kotlinx.html](https://github.com/Kotlin/kotlinx.html) by JetBrains. The type-safe HTML builder that makes this DSL possible. Without a builder API that treats HTML as a first-class Kotlin construct, there would be nothing to wrap.

## Ktor

[Ktor](https://ktor.io/) by JetBrains. The Kotlin-native web framework used in the example application. Its `respondHtml` integration with kotlinx.html makes server-side rendering feel natural.

## htmx

[htmx](https://htmx.org/) by Carson Gross. The library that makes progressive loading work without a JavaScript framework. The idea that the server can return HTML fragments and the browser can swap them in — without writing a single line of JavaScript — is what makes the example app's architecture possible.
