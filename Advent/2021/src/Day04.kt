const val BOARD_SIZE = 5

typealias Board<T> = List<List<T>>
typealias MutableBoard<T> = List<MutableList<T>>

data class Bingo(
  val numbers: MutableList<Int>,
  val boards: List<Board<Int>>,
  val map: List<MutableBoard<Boolean>>,
) {
  companion object {
    fun construct(input: List<String>) = Bingo(
      numbers = input.first().split(',').map(String::toInt).toMutableList(),
      boards = input.drop(1).filterNot(String::isBlank)
        .map { it.trim().split("\\s+".toRegex()).map(String::toInt) }.chunked(BOARD_SIZE),
      map = List(input.drop(1).filterNot(String::isBlank).chunked(BOARD_SIZE).size) {
        List(BOARD_SIZE) { MutableList(BOARD_SIZE) { false } }
      })
  }

  fun invoke(): Int {
    checkNumber(numbers.first())
    return checkWinOrNull(numbers.removeFirst()) ?: invoke()
  }

  fun invoke2(): Int {
    checkNumber(numbers.removeFirst())
    val lastBoardInd = checkLose() ?: invoke2()

    return if (lastBoardInd in map.indices) evaluateLastBoard(lastBoardInd) else lastBoardInd
  }

  private fun evaluateLastBoard(lastBoardInd: Int): Int {
    checkNumber(numbers.first())
    return countWinOrNull(numbers.removeFirst(), lastBoardInd) ?: evaluateLastBoard(lastBoardInd)
  }

  private fun checkNumber(num: Int) {
    boards.forEachIndexed { x, board ->
      board.forEachIndexed { y, list ->
        list.forEachIndexed { z, value ->
          if (num == value) map[x][y][z] = true
        }
      }
    }
  }


  private fun transpose(board: Board<Boolean>) = (0 until BOARD_SIZE).map { board.map { list -> list[it] } }

  private fun checkLineWin(board: Board<Boolean>) = board.any { list -> list.all { it } }

  private fun checkBoardWin(board: Board<Boolean>) = checkLineWin(board) || checkLineWin(transpose(board))


  private fun checkWinOrNull(number: Int): Int? = map.indices.mapNotNull { countWinOrNull(number, it) }.singleOrNull()

  private fun countWinOrNull(number: Int, ind: Int): Int? = if (checkBoardWin(map[ind])) countWin(number, ind) else null

  private fun countWin(number: Int, board: Int): Int = number * boards[board].foldIndexed(0) { y, acc2, list ->
    list.foldIndexed(acc2) { z, acc, value -> acc + if (!map[board][y][z]) value else 0 }
  }


  private fun checkLose(): Int? =
    getLastBoardOrNull(map.indices.mapNotNull { if (checkBoardWin(map[it])) it else null })

  private fun getLastBoardOrNull(indices: List<Int>) =
    if (indices.count() == boards.size - 1) boards.indices.minus(indices.toSet()).singleOrNull() else null
}

fun main() {
  fun part1(input: List<String>) = Bingo.construct(input).invoke()

  fun part2(input: List<String>) = Bingo.construct(input).invoke2()

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("input_test")
  check(part1(testInput) == 4512)
  check(part2(testInput) == 1924)

  val input = readInput("input")
  println(part1(input))
  println(part2(input))
}
