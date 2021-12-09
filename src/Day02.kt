fun main() {
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
    val testInput = readInput("Day02_test").map { it.split(' ') }

    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02").map { it.split(' ') }
    println(part1(input))
    println(part2(input))
}
