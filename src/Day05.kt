import java.util.Objects

class Point(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other !is Point) {
            return false
        }

        return x == other.x && y == other.y
    }

    override fun toString(): String {
        return listOf(x, y).toString()
    }

    override fun hashCode(): Int {
        return Objects.hash(x, y)
    }
}

class Line(private val start: Point, private val end: Point) {
    fun getLinePoints(diagonal: Boolean = false): List<Point> {
        val intersectPoints = mutableListOf<Point>()

        // horizontal line
        if (start.x == end.x) {
            val startY = listOf(start.y, end.y).minOrNull()
            val endY = listOf(start.y, end.y).maxOrNull()

            if (startY == null || endY == null) {
                throw Exception()
            }

            for (y in startY..endY) {
                intersectPoints.add(Point(start.x, y))
            }
        // vertical line
        } else if (start.y == end.y) {
            val startX = listOf(start.x, end.x).minOrNull()
            val endX = listOf(start.x, end.x).maxOrNull()

            if (startX == null || endX == null) {
                throw Exception()
            }

            for (x in startX..endX) {
                intersectPoints.add(Point(x, start.y))
            }
        // diagonal line
        } else if (diagonal) {
            var xOperation = '+'
            var yOperation = '+'

            if (start.x >= end.x) {
                xOperation = '-'
            }

            if (start.y >= end.y) {
                yOperation = '-'
            }

            intersectPoints.add(start)

            var currentPoint = start
            while (currentPoint != end) {
                var x = currentPoint.x
                var y = currentPoint.y

                if (xOperation == '-') {
                    x -= 1
                } else {
                    x += 1
                }

                if (yOperation == '-') {
                    y -= 1
                } else {
                    y += 1
                }

                currentPoint = Point(x, y)
                intersectPoints.add(currentPoint)
            }
        }

        return intersectPoints
    }

    override fun toString(): String {
        return listOf(start, end).toString()
    }
}

fun main() {
    val part1TestResult = 5
    val part2TestResult = 12

    fun getOverlappingLineCount(lines: List<Line>, diagonal: Boolean = false): Int {
        val allPoints = mutableListOf<Point>()
        lines.forEach {
            allPoints.addAll(it.getLinePoints(diagonal))
        }

        val maxX = allPoints.map { it.x }.maxOrNull()
        val maxY = allPoints.map { it.y }.maxOrNull()

        if (maxX == null || maxY == null) {
            throw Exception()
        }

        val pointMap = List(maxY+1) { MutableList(maxX+1) { 0 } }

        allPoints.forEach { point ->
            pointMap[point.y][point.x]++
        }

        return pointMap.fold(0) { totalSum, row -> totalSum + row.fold(0) { rowSum, col -> if (col >= 2) rowSum+1 else rowSum } }
    }

    fun part1(lines: List<Line>): Int {
        return getOverlappingLineCount(lines, false)
    }

    fun part2(lines: List<Line>): Int {
        return getOverlappingLineCount(lines, true)
    }

    fun executeWithFile(name: String): List<Int> {
        val lines = readInput(name).map { line ->
            val points = line.split("->").map { point ->
                val coords = point.trim().split(",").map { it.toInt() }
                Point(coords[0], coords[1])
            }
            Line(points[0], points[1])
        }

        val part1 = part1(lines)
        val part2 = part2(lines)

        return listOf(part1, part2)
    }

    // test if implementation meets criteria from the description, like:
    val testOutput = executeWithFile("Day05_test")
    check(testOutput[0] == part1TestResult)
    check(testOutput[1] == part2TestResult)

    val output = executeWithFile("Day05")
    println(output[0])
    println(output[1])
}