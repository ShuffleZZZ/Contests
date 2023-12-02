private val stone_maximums = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14,
)

private val game_regex = "Game (\\d+): (.*?)".toRegex()
private fun parseStone(s: String) = s.split(' ').let { it.first().toInt() to it.last() }
private fun parseInput(s: String) = game_regex.matchEntire(s)!!.destructured
    .let { (_, subsets) -> subsets.split("; ", ", ").map(::parseStone) }

fun main() {
    fun part1(input: List<String>) = input.map(::parseInput).withIndex()
        .filter { it.value.all { (amount, color) -> amount <= stone_maximums[color]!! } }
        .sumOf { it.index + 1 }

    fun part2(input: List<String>) = input.map(::parseInput)
        .sumOf { game ->
            val stones = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
            game.forEach { (amount, color) -> stones[color] = maxOf(stones[color]!!, amount) }

            stones.values.reduce(Int::times)
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("input")
    println(part1(input))
    println(part2(input))
}
