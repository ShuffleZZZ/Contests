fun Char.isOpen() = when (this) {
  '[', '(', '{', '<' -> true
  else -> false
}

fun Char.isClosed() = isOpen().not()

fun Char.matching() = when (this) {
  '[' -> ']'
  '(' -> ')'
  '{' -> '}'
  '<' -> '>'
  ']' -> '['
  ')' -> '('
  '}' -> '{'
  '>' -> '<'
  else -> error("Not a bracket")
}

fun Char.matches(other: Char) = this.matching() == other

fun Char.points() = when (this) {
  ')' -> 3
  ']' -> 57
  '}' -> 1197
  '>' -> 25137
  else -> error("Not a bracket")
}

fun Char.matchingPoints() = when (this) {
  ')' -> 1
  ']' -> 2
  '}' -> 3
  '>' -> 4
  else -> error("Not a bracket")
}

private fun ArrayDeque<Char>.safePop(it: Char) = if (this.last().matches(it)) this.removeLastOrNull() != null else false

private fun ArrayDeque<Char>.complete(): List<Char> {
  while (this.last().isOpen()) this.addFirst(this.removeLast().matching())

  return this.toList().reversed()
}


fun main() {
  fun part1(input: List<String>) = input.map { string ->
    ArrayDeque<Char>().let { stack ->
      string.mapIndexedNotNull { ind, it ->
        if (it.isOpen()) stack.add(it).let { null } else if (stack.safePop(it)) null else ind
      }.firstOrNull()?.let { string[it] }
    }
  }.sumOf { it?.points() ?: 0 }

  fun part2(input: List<String>) = input.mapNotNull { string ->
    ArrayDeque<Char>().let { stack ->
      string.mapIndexedNotNull { ind, it ->
        if (it.isOpen()) stack.add(it).let { null } else if (stack.safePop(it)) null else ind
      }.let { if (it.isEmpty()) stack else null }
    }
  }.map { it.complete().map { br -> br.matchingPoints() }.reduce { acc, br -> acc * 5 + br } }.sorted()
    .let { it[it.size / 2] }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("input_test")
  check(part1(testInput) == 26_397)
  check(part2(testInput) == 288_957)

  val input = readInput("input")
  println(part1(input))
  println(part2(input))
}
