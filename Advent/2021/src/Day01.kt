fun main() {
  fun part1(input: List<Int>) = input.zipWithNext().count { (a, b) -> a < b }

  fun part2(input: List<Int>) = input.zipWithNext().zipWithNext { (a, b), (_, d) -> listOf(a, b, d) }
    .zipWithNext().count { (a, b) -> a.sum() < b.sum() }

  // test if implementation meets criteria from the description, like:
  val testInput = readInts("input_test")
  check(part1(testInput) == 7)
  check(part2(testInput) == 5)

  val input = readInts("input")
  println(part1(input))
  println(part2(input))
}
