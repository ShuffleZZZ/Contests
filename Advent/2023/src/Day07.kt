sealed class Combination(private val hand: List<Int>, private val rank: Int) : Comparable<Combination> {
  override fun compareTo(other: Combination) =
    if (this.rank == other.rank) this.hand.compareTo(other.hand)
    else this.rank.compareTo(other.rank)
}

data class      Five(val hand: List<Int>) : Combination(hand, 7)
data class      Four(val hand: List<Int>) : Combination(hand, 6)
data class FullHouse(val hand: List<Int>) : Combination(hand, 5)
data class     Three(val hand: List<Int>) : Combination(hand, 4)
data class   TwoPair(val hand: List<Int>) : Combination(hand, 3)
data class   OnePair(val hand: List<Int>) : Combination(hand, 2)
data class      High(val hand: List<Int>) : Combination(hand, 1)


private fun countCards(cards: List<Int>) = cards.groupingBy { it }.eachCount()
private fun countCards2(cards: List<Int>) = countCards(cards).toMutableMap().also { counts ->
  val mostCommonKey = counts.keys.sortedByDescending { counts[it]!! }
    .filterNot { it == 1 }.firstOrNull() ?: cardToRank('A')
  counts[mostCommonKey] = (counts[mostCommonKey] ?: 0) + (counts.remove(1) ?: 0)
}.toMap()

private fun List<Int>.toCombination(counter: (List<Int>) -> Map<Int, Int> = ::countCards) =
  counter(this).values.sortedDescending().let { counts ->
    when (counts.first()) {
      5 -> Five(this)
      4 -> Four(this)
      3 -> if (counts.elementAt(1) == 2) FullHouse(this) else Three(this)
      2 -> if (counts.elementAt(1) == 2) TwoPair(this) else OnePair(this)
      else -> High(this)
    }
  }

private fun cardToRank2(card: Char) = if (card == 'J') 1 else cardToRank(card)
private fun cardToRank(card: Char) = when (card) {
  'A' -> 14
  'K' -> 13
  'Q' -> 12
  'J' -> 11
  'T' -> 10
  else -> card.digitToInt()
}

private fun handToCombination(hand: String) = hand.map { cardToRank(it) }.toCombination()
private fun handToCombination2(hand: String) = hand.map { cardToRank2(it) }.toCombination(::countCards2)

fun main() {
  fun part1(input: List<String>, handHandler: (String) -> Combination = ::handToCombination) =
    input.asSequence().map { entry -> entry.split(' ') }.map { (hand, bid) -> handHandler(hand) to bid.toInt() }
      .sortedBy { it.first }.map { it.second }.withIndex().sumOf { (i, bid) -> bid * (i + 1) }

  fun part2(input: List<String>) = part1(input, ::handToCombination2)

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("test")
  check(part1(testInput) == 6440)
  check(part2(testInput) == 5905)

  val input = readInput("input")
  println(part1(input))
  println(part2(input))
}
