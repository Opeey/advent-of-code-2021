fun main() {
    fun part1(operations: List<String>): Int {
        var depth = 0
        var horizontalPosition = 0

        for ((operation, count) in operations.map { it.split(' ') }) {
            when (operation) {
                "forward" -> horizontalPosition += count.toInt()
                "down" -> depth += count.toInt()
                "up" -> depth -= count.toInt()
            }
        }

        return depth * horizontalPosition
    }

    fun part2(operations: List<String>): Int {
        var depth = 0
        var horizontalPosition = 0
        var aim = 0

        for ((operation, count) in operations.map { it.split(' ') }) {
            when (operation) {
                "forward" -> {
                    horizontalPosition += count.toInt()
                    depth += (aim * count.toInt())
                }
                "down" -> aim += count.toInt()
                "up" -> aim -= count.toInt()
            }
        }

        return depth * horizontalPosition
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")

    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
