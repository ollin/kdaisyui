# test-and-clean-codegen

Give codegen/ unit tests and clear the code smells in test-generator.js. The generator has never had a test of its own: its only safety net is CI's generated-sources-drift job, which is a total end-to-end oracle but says nothing about which function broke. CodeScene scores test-generator.js at 8.26 with Complex Method (cc 13, 11, 10), Deep Nested Complexity (depth 4) and a Bumpy Road. Tests come first so the refactoring is provably behaviour-preserving; drift stays the second, independent check.
