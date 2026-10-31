# Findings

## F1 - The baseline the signals were written against had moved

- **What happened:** `probe.md` states its thresholds against 560 classes and 412 KB / 49.2 KB
  gzip. On 2026-10-31 the library emitted 551 classes, and the consumer stylesheet weighed
  373.8 KB / 46.0 KB minified.
- **Cause:** six weeks and 0.8.0 between proposing the probe and running it, and the original
  figures did not say whether they were minified.
- **Cost:** none in time. The thresholds were applied as written, and each lands the same way
  absolute or rescaled. A threshold close to the line would have needed a decision.

## F2 - The first compile measured nothing

- **What happened:** six stylesheets from very different class lists came out byte-identical.
- **Cause:** Tailwind 4 detects sources from its working directory unless the import says
  `source(none)`, and the directory held every list and the library sources.
- **Cost:** about 10 minutes of unplanned work. Read as a result, it would have produced a false
  dampen for the wrong reason.

## F3 - `example-app`'s stylesheet depends on automatic source detection without saying so

- **What happened:** `example-app/src/main/css/app.css` declares its `@source` paths but not
  `source(none)`, so Tailwind also scans whatever else is mounted under its working directory.
- **Cause:** the default was never stated in the entry CSS.
- **Cost:** none today. The task mounts only what it declares. Pre-existing, found on the way.

## F4 - `probe.md` refers to a step 6 that does not exist

- **What happened:** the amplify signal says "Two out of three is step 6", and the experiment has
  five steps.
- **Cause:** an edit to the step list that did not reach the signal text.
- **Cost:** none, but it left a reader guessing whether a step had been lost.

## Proposals

One row per finding (house rules §9).

| Finding | Proposal | Status |
|---|---|---|
| F1 | A probe states each threshold as a share of a baseline it measures with a named command, so a moved baseline rescales the threshold instead of leaving it stale | open |
| F2 | - | none - record only; `observations.md` keeps the `source(none)` lesson |
| F3 | `example-app`'s `app.css` says `source(none)`, so its stylesheet depends only on the sources it declares | adopted -> per-component-class-delivery |
| F4 | - | none - record only |
