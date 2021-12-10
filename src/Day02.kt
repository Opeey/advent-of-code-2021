fun main() {
    val part1TestResult = 150
    val part2TestResult = 900

    fun loadAndPrepareInput(name: String): List<List<String>> {
        return readInput(name).map { it.split(' ') }
    }

    fun part1(operations: List<List<String>>): Int {
        var depth = 0
        var horizontalPosition = 0

        for ((operation, countString) in operations) {
            val count = countString.toInt()
            when (operation) {
                "forward" -> horizontalPosition += count
                "down" -> depth += count
                "up" -> depth -= count
            }
        }

        return depth * horizontalPosition
    }

    fun part2(operations: List<List<String>>): Int {
        var depth = 0
        var horizontalPosition = 0
        var aim = 0

        for ((operation, countString) in operations) {
            val count = countString.toInt()
            when (operation) {
                "forward" -> {
                    horizontalPosition += count
                    depth += (aim * count)
                }
                "down" -> aim += count
                "up" -> aim -= count
            }
        }

        return depth * horizontalPosition
    }

    // test if implementation meets criteria from the description, like:
    val testInput = loadAndPrepareInput("Day02_test")
    check(part1(testInput) == part1TestResult)
    check(part2(testInput) == part2TestResult)

    val input = loadAndPrepareInput("Day02")
    println(part1(input))
    println(part2(input))
}
