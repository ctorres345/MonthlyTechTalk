//Creating costant for the required places
const val SCHOOL = "School"
const val GYM = "Gym"
const val OFFICE = "Office"

//Creating base data for the address list
val addressList = listOf(
        setOf(SCHOOL),
        setOf(GYM),
        setOf(GYM, SCHOOL),
        setOf(SCHOOL),
        setOf(SCHOOL, OFFICE),
)

fun main() {
    val result = apartmentSearch(setOf(SCHOOL, GYM, OFFICE), addressList)
    println("Desired Block Index: ${result.first} || Minimum Distance: ${result.second}")
}

fun apartmentSearch(requiredBuildings: Set<String>, addressList: List<Set<String>>): Pair<Int, Int> {
    //Initialize Values
    var tail = 0
    var head = 0
    var toFindCount = 0
    val buildingsToFind : MutableMap<String, Int> = mutableMapOf()
    var minWindow = addressList.size
    var minDist = minWindow / 2

    //Fill the buildingsToFindMap with the required buildings
    requiredBuildings.forEach { requiredBuilding ->
        if(requiredBuilding !in buildingsToFind) {
            buildingsToFind[requiredBuilding] = 0
        }
    }
    //Update count of buildings to find
    toFindCount = buildingsToFind.size

    //While we have buildings to find and the head haven't reach the end keep scanning over the address list
    while (buildingsToFind.isNotEmpty() && head < addressList.size) {
        if(toFindCount > 0) {
            println("$toFindCount Buildings Left to Find, Increase Window")
            val headBlock = addressList[head]

            headBlock.forEach { building ->
                if(building in buildingsToFind) {
                    val ocurrence = buildingsToFind.getValue(building)
                    println("Found $building with ocurrence $ocurrence in Map -- Increase Ocurrence")
                    if(ocurrence == 0) {
                        toFindCount--
                        println("Buildings left to find: $toFindCount")
                    }
                    buildingsToFind[building] = ocurrence + 1
                }
            }
            head++
        }

        while(toFindCount == 0) {
            println("No More Buildings to Find, Decrease Window")
            minWindow = minOf(minWindow, head - tail)
            println("Current Min Window : $minWindow")
            val tailBlock = addressList[tail]
            tailBlock.forEach { building ->
                if(building in buildingsToFind) {
                    val ocurrence = buildingsToFind.getValue(building)
                    println("Found $building with ocurrence $ocurrence in Map -- Decrease ocurrence")
                    buildingsToFind[building] = ocurrence - 1
                    if(buildingsToFind[building] == 0) {
                        toFindCount++
                        println("Buildings left to find: $toFindCount")
                    }
                }
            }
            if(toFindCount > 0 && head == addressList.size) {
                println("Cant minimize anymore, STOP")
                break
            } else {
                println("Keep minimizing window")
                tail++
            }
        }
        minDist = minWindow / 2
        println("Minimum Distance To travel $minDist")
    }
    //Return a pair with the result place to choose and the minimum distance to travel
    return Pair(tail + minDist, minDist)
}