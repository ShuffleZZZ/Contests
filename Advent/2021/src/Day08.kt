typealias Output = Set<Char>

fun getCharsByLength(input: List<Output>, size: Int) = input.filter { it.size == size }
fun getCharsByUniqueLength(input: List<Output>, size: Int) = input.first { it.size == size }

fun ONE(input: List<Output>) = getCharsByUniqueLength(input, 2)
fun SEVEN(input: List<Output>) = getCharsByUniqueLength(input, 3)
fun FOUR(input: List<Output>) = getCharsByUniqueLength(input, 4)
fun EIGHT(input: List<Output>) = getCharsByUniqueLength(input, 7)

fun SIX(input: List<Output>) = getCharsByLength(input, 6).first { maybeSix ->
  (EIGHT(input) - SEVEN(input)).all { it in maybeSix }
}

fun FIVE(input: List<Output>) = getCharsByLength(input, 5).first { maybeFive ->
  maybeFive.all { it in SIX(input) }
}


fun findTop(input: List<Output>) = (SEVEN(input) - ONE(input)).single()

fun findBottom(top: Char, input: List<Output>) =
  getCharsByLength(input, 6).firstNotNullOf { (it - top - FOUR(input)).singleOrNull() }

fun findMid(bottom: Char, input: List<Output>) =
  getCharsByLength(input, 5).firstNotNullOf { (it - bottom - SEVEN(input)).singleOrNull() }


fun findTopRight(input: List<Output>) = (EIGHT(input) - SIX(input)).first()
fun findBottomLeft(input: List<Output>) = (SIX(input) - FIVE(input)).first()

fun findBottomRight(input: List<Output>, known: Output) = (SEVEN(input) - known).single()
fun findTopLeft(input: List<Output>, known: Output) = (EIGHT(input) - known).single()

private fun String.toNumber(mapping: List<Char>): Int = when {
  this.length == 2 -> 1
  this.length == 3 -> 7
  this.length == 4 -> 4
  this.length == 7 -> 8
  this.length == 6 && mapping[4] !in this -> 9
  this.length == 6 && mapping[1] !in this -> 6
  this.length == 5 && mapping[1] !in this && mapping[4] !in this -> 5
  this.length == 5 && mapping[2] !in this && mapping[5] !in this -> 2
  this.length == 5 && mapping[4] !in this && mapping[5] !in this -> 3
  else -> 0
}

fun main() {
  fun part1(input: List<String>) =
    input.map { it.substringAfter('|').trim().split(' ') }.flatten().count { it.length in listOf(2, 3, 4, 7) }

  fun part2(input: List<String>) = input.map { line -> line.split(" | ").map { it.split(" ") } }.map { (left, right) ->
    left.map { it.toSet() }.let { strings ->
      MutableList(7) { 'ö' }.let {
        it[0] = findTop(strings)
        it[1] = findTopRight(strings)
        it[3] = findBottom(it[0], strings)
        it[4] = findBottomLeft(strings)
        it[6] = findMid(it[3], strings)

        it[2] = findBottomRight(strings, it.filterNot('ö'::equals).toSet())
        it[5] = findTopLeft(strings, it.filterNot('ö'::equals).toSet())

        it
      }.let { right.map { string -> string.toNumber(it) }.joinToString("").toInt() }
    }
  }.sumOf { it }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("input_test")
  check(part1(testInput) == 26)
  check(part2(testInput) == 61_229)

  val input = readInput("input")
  println(part1(input))
  println(part2(input))
}
