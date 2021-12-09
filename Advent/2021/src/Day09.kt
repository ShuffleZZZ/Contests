typealias Cell = Pair<Int, Int>
typealias Grid = List<List<Int>>

fun List<String>.parse() = this.map { it.map { char -> char.toString().toInt() } }

fun makeRange(size: Int, x: Int) = if (x - 1 >= 0) if (x + 1 <= size - 1) listOf(x - 1, x + 1) else listOf(x - 1)
else if (x + 1 <= size - 1) listOf(x + 1) else error("Corrupted grid")

fun checkLows(input: Grid, x: Int, y: Int) = makeRange(input.size, x).all { input[x][y] < input[it][y] }
  && makeRange(input[0].size, y).all { input[x][y] < input[x][it] }

fun populateRange(input: Grid, point: Cell, basin: MutableSet<Cell>) {
  if (point !in basin && input[point.first][point.second] < 9) {
    basin.add(point)
    populateBasin(input, point, basin)
  }
}

fun populateBasin(input: Grid, low: Cell, basin: MutableSet<Cell>): MutableSet<Cell> =
  makeRange(input.size, low.first).forEach { populateRange(input, it to low.second, basin) }
    .let { makeRange(input[0].size, low.second).forEach { populateRange(input, low.first to it, basin) } }.let { basin }

fun main() {
  fun part1(input: Grid) =
    input.mapIndexed { x, list -> list.filterIndexed { y, _ -> checkLows(input, x, y) } }.flatten().sumOf { it + 1 }

  fun part2(input: Grid) = input.asSequence().mapIndexed { x, list ->
    list.mapIndexedNotNull { y, _ -> if (checkLows(input, x, y)) y else null }.map { x to it }
  }.flatten().map { populateBasin(input, it, mutableSetOf(it)) }.map { it.size }.sortedDescending().take(3)
    .reduce(Int::times)

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("input_test").parse()
  check(part1(testInput) == 15)
  check(part2(testInput) == 1134)

  val input = readInput("input").parse()
  println(part1(input))
  println(part2(input))
}
