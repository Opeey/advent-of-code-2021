fun main() {
    fun part1(measurements: List<Int>): Int {
        return measurements.windowed(2).fold(0) { count, window -> if (window[1] > window[0]) count + 1 else count }
    }

    fun part2(measurements: List<Int>): Int {
        return part1(measurements.windowed(3).map{ it.sum() })
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").map { it.toInt() }
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
