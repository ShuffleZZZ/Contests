data class Instruction(
  val x: Int,
  val y: Int,
  val depth: Int = 0,
) {
  companion object {
    private fun parse(line: String) = line.split(' ', limit = 2).let { it.first() to it.last().toInt() }

    fun construct(line: String): Instruction {
      val (direction, value) = parse(line)

      return when (direction) {
        "up" -> Instruction(-value, 0)
        "down" -> Instruction(value, 0)
        else -> Instruction(0, value)
      }
    }
  }

  fun fold(other: Instruction) = Instruction(x + other.x, y + other.y, depth + x * other.y)

  fun times1() = x * y
  fun times2() = y * depth
}

fun main() {
  fun part1(input: List<String>) = input.map(Instruction::construct).reduce(Instruction::fold).times1()
  fun part2(input: List<String>) = input.map(Instruction::construct).reduce(Instruction::fold).times2()

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("input_test")
  check(part1(testInput) == 150)
  check(part2(testInput) == 900)

  val input = readInput("input")
  println(part1(input))
  println(part2(input))
}
