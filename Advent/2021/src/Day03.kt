fun Boolean.toInt() = if (this) 1 else 0

fun transpose(input: List<String?>) =
  (0 until input.filterNotNull().maxOf(String::length))
    .map { n -> input.map { it?.get(n) ?: "$" }.joinToString("") }.asSequence()

fun main() {
  fun part1(input: List<String>, isMostCommon: Boolean) = transpose(input)
    .map { it.count { char -> char == '1' } > input.size / 2 }
    .map { (it == isMostCommon).toInt() }
    .joinToString("").toInt(2)

  fun part1(input: List<String>) = part1(input, true) * part1(input, false)

  fun part2(input: List<String?>, isMostCommon: Boolean): Int = transpose(input)
    .first().let {
      ((it.count { char -> char == '1' } >= it.replace("$", "").length.toDouble() / 2) == isMostCommon)
        .toInt().toString()
        .let { bit -> input.mapIndexed { pos, str -> if (str?.get(0).toString() == bit) pos else null } }
        .filterNotNull()
        .let { list ->
          if (list.singleOrNull() != null) list.single()
          else part2(input.map { n -> n?.drop(1) }.mapIndexed { num, value -> if (list.contains(num)) value else null },
            isMostCommon)
        }
    }

  fun part2(input: List<String>) = input[part2(input, true)].toInt(2) * input[part2(input, false)].toInt(2)

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("input_test")
  check(part1(testInput) == 198)
  check(part2(testInput) == 230)

  val input = readInput("input")
  println(part1(input))
  println(part2(input))
}
