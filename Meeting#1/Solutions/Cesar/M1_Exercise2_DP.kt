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
            val ways = Array(totalStairs + 1) {
                if(it == 0) 1 else 0
            }
            for (i in 1..totalStairs) {
                var total = 0
                steps.forEach { step ->
                    val remainder = i - step
                    if(remainder >= 0) {
                        total += ways[remainder]
                    }
                }
                ways[i] = total
            }
            ways[totalStairs]
        }
    }
}