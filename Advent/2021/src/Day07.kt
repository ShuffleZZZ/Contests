import kotlin.math.absoluteValue

fun distance(x1: Int, x2: Int) = (0..(x2 - x1).absoluteValue).sum()

fun main() {
  fun part1(input: List<Int>) =
    input.sorted().let { it[it.size / 2].let { x -> it.sumOf { value -> (value - x).absoluteValue } } }

  fun part2(input: List<Int>) = (input.minOf { it }..input.maxOf { it }).minOf { value ->
    input.sumOf { distance(value, it) }
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readIntLine("input_test", ",")
  check(part1(testInput) == 37)
  check(part2(testInput) == 168)

  val input = readIntLine("input", ",")
  println(part1(input))
  println(part2(input))
}
