class BingoBoard(board: List<List<Int>>) {
    private val bingoBoard = board
    private val checkedCounts = List(board.size) { MutableList(board[0].size) { 0 } }

    fun check(number: Int) {
        bingoBoard.forEachIndexed { rowIndex, row ->
            val colIndex = row.indexOf(number)
            if (colIndex != -1) {
                checkedCounts[rowIndex][colIndex] = 1
            }
        }
    }

    fun hasWon(): Boolean {
        val rowSums = checkedCounts.map { it.sum() }
        val colSums = checkedCounts[0].indices.map { col -> checkedCounts.sumOf { row -> row[col] } }

        return rowSums.contains(checkedCounts.size) || colSums.contains(checkedCounts[0].size)
    }

    fun sumUnmarked(): Int {
        var sum = 0
        bingoBoard.forEachIndexed { rowIndex, _ ->
            bingoBoard[rowIndex].forEachIndexed { colIndex, col ->
                if (checkedCounts[rowIndex][colIndex] == 0) {
                    sum += col
                }
            }
        }

        return sum
    }

    override fun toString(): String {
        return bingoBoard.toString()
    }
}