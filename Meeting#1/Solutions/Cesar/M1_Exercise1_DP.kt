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
        else -> {
            val ways = Array(totalStairs + 1) {
                if(it < 2) 1 else 0
            }
            for (i in 2..totalStairs) {
                ways[i] = ways[i-1] + ways[i-2]
            }
            ways[totalStairs]
        }
    }
}