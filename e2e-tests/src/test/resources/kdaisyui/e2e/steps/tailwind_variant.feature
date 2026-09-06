Feature: Tailwind Variant Of A DaisyUI Class
  Whether lg:btn-lg actually applies, which docs/explanation.md promises consumers

  The control button carries btn-lg outright; the subject carries lg:btn-lg. Comparing the two
  needs no hard-coded size and survives any DaisyUI restyle.

  Scenario: The variant applies at the lg breakpoint
    Given the viewport is 1280 by 800
    And the user opens "/tailwind-variant"
    Then the elements "variant-control" and "variant-subject" have the same "height"

  Scenario: The variant does not apply below the lg breakpoint
    Given the viewport is 800 by 600
    And the user opens "/tailwind-variant"
    Then the elements "variant-control" and "variant-subject" differ in "height"

  Scenario: A max-width variant applies below its breakpoint
    Given the viewport is 800 by 600
    And the user opens "/tailwind-variant"
    Then the elements "variant-control" and "variant-subject-max" have the same "height"

  Scenario: A max-width variant does not apply above its breakpoint
    Given the viewport is 1280 by 800
    And the user opens "/tailwind-variant"
    Then the elements "variant-control" and "variant-subject-max" differ in "height"
