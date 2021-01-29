import kotlin.system.measureTimeMillis

fun main() {
    val runtime = measureTimeMillis {
        println("Total possible steps: ${totalWaysWithSteps(40, intArrayOf(1, 3, 5))}")
    }
    println("Runtime in ms : $runtime")
}

private fun totalWaysWithSteps(totalStairs: Int, steps: IntArray) : Int {
    return when(totalStairs) {
        0 -> 1
        else -> {
            var total = 0
            steps.forEach {
                val remainder = totalStairs - it
                if(remainder >= 0) {
                    total += totalWaysWithSteps(remainder, steps)
                }
            }
            total
        }
    }
}