import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

fun Boolean.toInt() = if (this) 1 else 0

fun <T: Comparable<T>> List<T>.compareTo(other: List<T>): Int {
  assert(this.size == other.size)

  for (i in this.indices) {
    if (this[i] != other[i]) return this[i].compareTo(other[i])
  }

  return 0
}

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
