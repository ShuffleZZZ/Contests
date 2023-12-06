private val emptySpace = "\\s+".toRegex()

private fun trimToValues(input: String) = input.substringAfter(':').trim()
private fun getInts(input: String) = trimToValues(input).split(emptySpace).map(String::toInt)
private fun getLong(input: String) = trimToValues(input).filterNot(Char::isWhitespace).toLong()

fun main() {
  fun part1(input: List<String>) = getInts(input.first()).zip(getInts(input.last())).map { (time, dist) ->
    (0..time).count { it * (time - it) > dist }
  }.reduce(Int::times)

  fun part2(input: List<String>): Long {
    val time = getLong(input.first())
    val dist = getLong(input.last())

    val centerPoint = time / 2
    val plotIntersection = (0..time).first { it * (time - it) > dist }

    val singleArcPoints = centerPoint + 1 - plotIntersection
    val extraCenterPoint = (centerPoint * 2 == time).toInt()

    return 2 * singleArcPoints - extraCenterPoint
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("test")
  check(part1(testInput) == 288)
  check(part2(testInput) == 71_503L)

  val input = readInput("input")
  println(part1(input))
  println(part2(input))
}
