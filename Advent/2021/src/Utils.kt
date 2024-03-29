import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun getInputFile(name: String) = File("src/input", "$name.txt")

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = getInputFile(name).readLines()

/**
 * Reads Int values placed one at each line.
 */
fun readInts(name: String, radix: Int = 10) = readInput(name).map { it.toInt(radix) }

fun readIntLine(name: String, separator: String = " ", radix: Int = 10) = readInput(name).map {
  it.split(separator).map(String::trim).map { chunk -> chunk.toInt(radix) }
}.single()

fun List<String>.parseInts() = this.map { it.map { char -> char.toString().toInt() } }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
