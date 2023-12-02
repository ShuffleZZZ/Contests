private val mappings = mapOf(
  "zero" to "0",
  "one" to "1",
  "two" to "2",
  "three" to "3",
  "four" to "4",
  "five" to "5",
  "six" to "6",
  "seven" to "7",
  "eight" to "8",
  "nine" to "9",
)

fun main() {
  fun part1(input: List<String>, values: Collection<String> = mappings.values) =
    input.map { s ->
      val l = s.findAnyOf(values)?.second
      val r = s.findLastAnyOf(values)?.second
      (mappings[l] ?: l) + (mappings[r] ?: r)
    }.sumOf { it.toInt() }

  fun part2(input: List<String>) = part1(input, mappings.keys + mappings.values)

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("test")
  check(part1(testInput) == 142)
  val testInput2 = readInput("test2")
  check(part2(testInput2) == 281)

  val input = readInput("input")
  println(part1(input))
  println(part2(input))
}
