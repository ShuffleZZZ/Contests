data class Template(val rules: Map<Pair<Char, Char>, Char>, var polymer: List<Char>) {
  fun step(times: Int) = repeat(times) {
    polymer = listOf(polymer.first()) + polymer.windowed(2).map { (first, second) ->
      if (rules.containsKey(first to second)) listOf(rules[first to second]!!, second)
      else listOf(second)
    }.flatten()
  }.let { polymer.groupingBy { it }.eachCount().values }
    .let { quantities -> quantities.maxOf { it } - quantities.minOf { it } }

  companion object {
    fun parse(input: List<String>) = Template(
      rules = input.drop(input.lastIndexOf("") + 1).map { it.split(" -> ") }
        .associate { (first, second) -> first[0] to first[1] to second[0] },
      polymer = input.first().trim().toList()
    )
  }
}

fun main() {
  fun part1(input: List<String>) = Template.parse(input).step(10)

  fun part2(input: List<String>) = Template.parse(input).step(40)

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("input_test")
  check(part1(testInput) == 1_588)
//  check(part2(testInput) == 2_188_189_693_529)

  val input = readInput("input")
  println(part1(input))
//  println(part2(input))
}
