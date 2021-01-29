import kotlin.system.measureTimeMillis

fun main() {
    val runtime = measureTimeMillis {
        println("Total possible steps: ${totalWays(40)}")
    }
    println("Runtime in ms : $runtime")
}

private fun totalWays(totalStairs: Int) : Int {
    return when(totalStairs) {
        in 0..1 -> 1
        else -> totalWays(totalStairs-1) + totalWays(totalStairs-2)
    }
}