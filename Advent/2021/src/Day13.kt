data class ThermalImage(val grid: MutableSet<Pair<Int, Int>>, val folds: MutableList<Pair<Int, Int>>) {
  fun fold() = folds.first().let {
    grid.toSet().forEach { key ->
      if (it.first == 0) {
        if (it.second < key.second) {
          grid.remove(key)
          grid.add(key.first to it.second - (key.second - it.second))
        }
      } else if (it.first < key.first) {
        grid.remove(key)
        grid.add(it.first - (key.first - it.first) to key.second)
      }
    }

    grid.size
  }

  fun foldAll(): String {
    while (folds.isNotEmpty()) {
      this.fold()
      folds.removeFirst()
    }

    return grid.printable()
  }

  companion object {
    fun parse(input: List<String>) = ThermalImage(grid = input.take(input.lastIndexOf(""))
      .map { it.substringBefore(",").toInt() to it.substringAfter(",").toInt() }.toMutableSet(),
      folds = input.asSequence().drop(input.lastIndexOf("")).filterNot { it.isEmpty() }.map { string ->
        "fold along ([x-y])=(\\d+)".toRegex().matchEntire(string)?.groups?.map { it!!.value }?.drop(1)?.take(2)!!
      }.map { (direction, value) -> if (direction == "x") value.toInt() to 0 else 0 to value.toInt() }.toMutableList())
  }
}

fun MutableSet<Pair<Int, Int>>.printable() = MutableList(this.map { it.first }.maxOf { it } + 1) {
  MutableList(this.map { it.second }.maxOf { it } + 1) { '.' }
}.let {
  this.forEach { key -> it[key.first][key.second] = 'ö' }
  (0 until it[0].size).joinToString("\n") { ind -> it.map { list -> list[ind] }.joinToString("") }
}


fun main() {
  fun part1(input: List<String>) = ThermalImage.parse(input).fold()

  fun part2(input: List<String>) = ThermalImage.parse(input).foldAll()

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("input_test")
  check(part1(testInput) == 17)
  check(part2(testInput) == TEST_OUTPUT)

  val input = readInput("input")
  println(part1(input))
  println(part2(input))
}

val TEST_OUTPUT =
  """
  ööööö
  ö...ö
  ö...ö
  ö...ö
  ööööö
  """.trimIndent()
