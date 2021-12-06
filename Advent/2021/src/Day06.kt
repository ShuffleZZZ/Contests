import java.util.*

const val DAYS_1 = 80
const val DAYS_2 = 256
const val INIT_TIMER = 8
const val RESET_TIMER = 6

fun parseInput(input: String) = MutableList(INIT_TIMER + 1) { 0L }.let { list ->
  readInput(input).single().split(',').map { it.trim().toInt() }.forEach { ind -> list[ind]++ }
    .let { list }
}

fun solve(input: MutableList<Long>, days: Int) = repeat(days) {
  Collections.rotate(input, -1)
  input[RESET_TIMER] += input[INIT_TIMER]
}.let { input.sum() }

fun main() {

  fun part1(input: MutableList<Long>) = solve(input, DAYS_1)

  fun part2(input: MutableList<Long>) = solve(input, DAYS_2)

  // test if implementation meets criteria from the description, like:
  val testInput = parseInput("input_test")
  check(part1(testInput.toMutableList()) == 5_934L)
  check(part2(testInput) == 26_984_457_539)

  val input = parseInput("input")
  println(part1(input.toMutableList()))
  println(part2(input))
}
