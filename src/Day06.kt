fun main() {
    val part1TestResult = 5934L
    val part2TestResult = 26984457539L

    fun simulateFishPopulation(fish: MutableMap<Int, Long>, days: Int): MutableMap<Int, Long> {
        for (day in 1..days) {
            val newFish = fish[0] ?: throw Exception()

            for (dayCounter in 0..8) {
                var nextDay = 0L

                if (dayCounter != 8) {
                    nextDay = fish[dayCounter + 1] ?: throw Exception()
                }

                when (dayCounter) {
                    6 -> {
                        fish[dayCounter] = nextDay + newFish
                    }
                    8 -> {
                        fish[dayCounter] = newFish
                    }
                    else -> {
                        fish[dayCounter] = nextDay
                    }
                }
            }
        }

        return fish
    }

    fun part1(fishPopulation: MutableMap<Int, Long>): Long {
        return simulateFishPopulation(fishPopulation, 80).map { it.value }.sum()
    }

    fun part2(fishPopulation: MutableMap<Int, Long>): Long {
        return simulateFishPopulation(fishPopulation, 256).map { it.value }.sum()
    }

    fun executeWithFile(name: String): List<Long> {
        val fish = readInput(name)[0].split(",").map { it.toInt() }.toMutableList()
        val fishPopulation = mutableMapOf<Int, Long>()

        for (dayCounter in 0..8) {
            fishPopulation[dayCounter] = fish.count { it == dayCounter }.toLong()
        }

        val part1 = part1(fishPopulation.toMutableMap())
        val part2 = part2(fishPopulation.toMutableMap())

        return listOf(part1, part2)
    }

    // test if implementation meets criteria from the description, like:
    val testOutput = executeWithFile("Day06_test")
    check(testOutput[0] == part1TestResult)
    check(testOutput[1] == part2TestResult)

    val output = executeWithFile("Day06")
    println(output[0])
    println(output[1])
}