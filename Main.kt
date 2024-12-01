import java.io.File
import kotlin.math.abs

fun main() {
    val lines = File("input.txt").readLines()
    val firstGroupArray = ArrayList<Int>()
    val secondGroupArray = ArrayList<Int>()
    lines.map {
        val splittedString = it.split(" ")
        firstGroupArray.add(splittedString[0].toInt())
        secondGroupArray.add(splittedString[splittedString.size -1].toInt())
    }
    firstGroupArray.sort()
    secondGroupArray.sort()

    calculateDistance(firstGroupArray, secondGroupArray)
    calculateSimilarity(firstGroupArray, secondGroupArray)
}

fun calculateDistance(firstArray: ArrayList<Int>, secondArray: ArrayList<Int>) {
    var dist = 0
    for (i in firstArray.indices) {
        dist = dist + abs(firstArray[i] - secondArray[i])
    }

    println(dist)
}

fun calculateSimilarity(firstArray: ArrayList<Int>, secondArray: ArrayList<Int>) {
    val secondArrayOccurencesMap = secondArray.groupingBy { it }.eachCount()
    println(firstArray.map { it*(secondArrayOccurencesMap[it] ?: 0) }.sum())
}