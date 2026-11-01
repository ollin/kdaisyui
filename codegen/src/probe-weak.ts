/** A probe: a function whose test runs it and asserts nothing it returns. */
export function doubled(value: number): number {
  return value > 10 ? value * 2 : value + value
}
