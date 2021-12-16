import com.sun.tools.corba.se.idl.constExpr.BooleanNot

fun main() {
    val part1TestResult = 4512
    val part2TestResult = 1924

    fun part1(gameInput: List<Int>, gameBoards: List<BingoBoard>): Int {
        gameInput.forEach { input ->
            gameBoards.forEach { gameBoard ->
                gameBoard.check(input)

                if (gameBoard.hasWon()) {
                    return gameBoard.sumUnmarked() * input
                }
            }
        }

        return 0
    }

    fun part2(gameInput: List<Int>, gameBoards: List<BingoBoard>): Int {
        val wonGameBoards = mutableListOf<BingoBoard>()

        gameInput.forEach { input ->
            gameBoards.forEach { gameBoard ->
                gameBoard.check(input)

                if (!wonGameBoards.contains(gameBoard) && gameBoard.hasWon()) {
                    if (wonGameBoards.size == gameBoards.size - 1) {
                        return gameBoard.sumUnmarked() * input
                    }

                    wonGameBoards.add(gameBoard)
                }
            }
        }

        return 0
    }

    fun executeWithFile(name: String): List<Int> {
        val input = readInput(name)
        var gameInput = listOf<Int>()
        val gameBoards = mutableListOf<BingoBoard>()

        var currentBoard = mutableListOf<List<Int>>()
        for ( (lineNumber, line) in input.withIndex()) {
            if (lineNumber == 0) {
                gameInput = line.split(',').map { it.toInt() }
                continue
            }

            if (line == "") {
                if (currentBoard.size > 0) {
                    gameBoards.add(BingoBoard(currentBoard))
                }

                currentBoard = mutableListOf()
                continue
            }

            currentBoard.add(line.windowed(2, 3).map { it.trim().toInt() })
        }
        gameBoards.add(BingoBoard(currentBoard))

        val part1 = part1(gameInput, gameBoards)
        val part2 = part2(gameInput, gameBoards)

        return listOf(part1, part2)
    }

    // test if implementation meets criteria from the description, like:
    val testOutput = executeWithFile("Day04_test")
    check(testOutput[0] == part1TestResult)
    check(testOutput[1] == part2TestResult)

    val output = executeWithFile("Day04")
    println(output[0])
    println(output[1])
}