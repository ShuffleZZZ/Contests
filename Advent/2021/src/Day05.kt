typealias Point = Pair<Int, Int>

data class Line(val start: Point, val end: Point) {
  companion object {
    private fun parsePoint(point: String) = point.split(',').map(String::trim).map(String::toInt).zipWithNext().first()

    fun parse(line: String) = Line(
      start = parsePoint(line.substringBefore("->")),
      end = parsePoint(line.substringAfter("->")))
  }

  fun markedPoints(useDiag: Boolean): List<Point> = when {
    start.first == end.first && start.second != end.second ->
      markLine(start.second, end.second).map { start.first to it }
    start.first != end.first && start.second == end.second ->
      markLine(start.first, end.first).map { it to start.second }
    useDiag && start.first - end.first == start.second - end.second -> markDiag(leftBottomPoint(), notLeftBottomPoint())
    useDiag -> markDiag(leftBottomPoint(), notLeftBottomPoint())
    else -> emptyList()
  }

  private fun markLine(x: Int, y: Int) = if (x > y) y..x else x..y

  private fun orderedRange(x: Int, y: Int) = if (x < y) x..y else x downTo y

  private fun markDiag(start: Point, end: Point) =
    orderedRange(start.first, end.first).zip(orderedRange(start.second, end.second))

  private fun leftBottomPoint() = when {
    start.first < end.first -> start
    start.first == end.first && start.second < end.second -> start
    else -> end
  }

  private fun notLeftBottomPoint() = if (leftBottomPoint() == end) start else end
}

fun MutableMap<Point, Int>.fill(points: List<Point>) =
  points.forEach { value -> this.merge(value, 1, Int::plus) }.let { this }

fun main() {

  fun part1(input: List<String>) =
    mutableMapOf<Point, Int>().fill(input.map(Line::parse).flatMap { it.markedPoints(false) }).values.count { it > 1 }

  fun part2(input: List<String>) =
    mutableMapOf<Point, Int>().fill(input.map(Line::parse).flatMap { it.markedPoints(true) }).values.count { it > 1 }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("input_test")
  println(part1(testInput))
  check(part1(testInput) == 5)
  println(part2(testInput))
  check(part2(testInput) == 12)

  val input = readInput("input")
  println(part1(input))
  println(part2(input))
}
