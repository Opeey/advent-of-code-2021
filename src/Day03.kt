fun main() {
    val part1TestResult = 198
    val part2TestResult = 230

    fun loadAndPrepareInput(name: String): List<List<Int>> {
        return readInput(name).map { it.chunked(1).map { bit -> bit.toInt()} }
    }

    fun getMatrixSum(matrix: List<List<Int>>): List<Int> {
        return matrix[0].indices.map { col -> matrix.sumOf { row -> row[col] } }
    }

    fun getMostCommonBit(matrix: List<List<Int>>, column: Int): Int {
        if (matrix.sumOf { row -> row[column] } >= matrix.size / 2.0) {
            return 1
        }

        return 0
    }

    fun getLeastCommonBit(matrix: List<List<Int>>, column: Int): Int {
        if (matrix.sumOf { row -> row[column] } >= matrix.size / 2.0) {
            return 0
        }

        return 1
    }

    fun part1(matrix: List<List<Int>>): Int {
        val gammaRateBinary = getMatrixSum(matrix).map { bitSum -> if (bitSum >= matrix.size / 2) "1" else "0" }.reduce {acc, bit -> acc + bit}
        val epsilonRateBinary = gammaRateBinary.map { char -> if (char == '1') '0' else '1' }.toCharArray().concatToString()

        return Integer.parseInt(gammaRateBinary, 2) * Integer.parseInt(epsilonRateBinary, 2)
    }

    fun part2(matrix: List<List<Int>>): Int {
        var oxygenGeneratorRatingMatrix = matrix
        var co2ScrubberRatingMatrix = matrix

        for (col in matrix[0].indices) {
            if (oxygenGeneratorRatingMatrix.size > 1) {
                val mostCommonBit = getMostCommonBit(oxygenGeneratorRatingMatrix, col)
                oxygenGeneratorRatingMatrix = oxygenGeneratorRatingMatrix.filter { it[col] == mostCommonBit }
            }

            if (co2ScrubberRatingMatrix.size > 1) {
                val leastCommonBit = getLeastCommonBit(co2ScrubberRatingMatrix, col)
                co2ScrubberRatingMatrix = co2ScrubberRatingMatrix.filter { it[col] == leastCommonBit }
            }
        }

        val oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatingMatrix[0].map { it.toString() }.reduce {acc, bit -> acc + bit},2)
        val co2ScrubberRating = Integer.parseInt(co2ScrubberRatingMatrix[0].map { it.toString() }.reduce {acc, bit -> acc + bit},2)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = loadAndPrepareInput("Day03_test")
    check(part1(testInput) == part1TestResult)
    check(part2(testInput) == part2TestResult)

    val input = loadAndPrepareInput("Day03")
    println(part1(input))
    println(part2(input))
}