fun main() {
    val part1TestResult = 7
    val part2TestResult = 5

    fun loadAndPrepareInput(name: String): List<Int> {
        return readInput(name).map { it.toInt() }
    }

    fun part1(measurements: List<Int>): Int {
        return measurements.windowed(2).fold(0) { count, window -> if (window[1] > window[0]) count + 1 else count }
    }

    fun part2(measurements: List<Int>): Int {
        return part1(measurements.windowed(3).map{ it.sum() })
    }

    // test if implementation meets criteria from the description, like:
    val testInput = loadAndPrepareInput("Day01_test")
    check(part1(testInput) == part1TestResult)
    check(part2(testInput) == part2TestResult)

    val input = loadAndPrepareInput("Day01")
    println(part1(input))
    println(part2(input))
}
