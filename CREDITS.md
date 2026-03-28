# Credits and Acknowledgements

## DaisyUI

This project would not exist without [DaisyUI](https://daisyui.com/) by [Pouya Saadeghi](https://github.com/saadeghi).

DaisyUI does something genuinely hard: it takes Tailwind CSS — a utility-first framework that produces verbose, hard-to-read class strings — and turns it into a semantic component system. Instead of `class="flex items-center justify-between px-4 py-2 rounded-lg bg-blue-600 text-white font-medium hover:bg-blue-700"`, you write `class="btn btn-primary"`. That is a meaningful abstraction, done right.

The example dashboard in this project (`example-app/`) is based on the [HTML Dashboard](https://daisyui.com/store/html-dashboard/) template from the DaisyUI store. A license was purchased for this project. The template served as the design reference and target for the kdaisyUI component DSL — the goal was to be able to reproduce that dashboard entirely from Kotlin code.

If Pouya or anyone from the DaisyUI team has questions or concerns about how the template is used here, please open an issue or reach out directly. The intent is to celebrate the work, not to exploit it.

## Tailwind CSS

[Tailwind CSS](https://tailwindcss.com/) by Adam Wathan and the Tailwind Labs team. The utility-first approach that DaisyUI builds on.

## kotlinx.html

[kotlinx.html](https://github.com/Kotlin/kotlinx.html) by JetBrains. The type-safe HTML builder that makes this DSL possible.

## Ktor

[Ktor](https://ktor.io/) by JetBrains. The Kotlin-native web framework used in the example application.

## htmx

[htmx](https://htmx.org/) by Carson Gross. The library that makes server-rendered progressive loading feel natural without a JavaScript framework.
