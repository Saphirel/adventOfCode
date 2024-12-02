import java.io.File
import kotlin.math.abs

fun main() {
    val splittedReports = File("day2_input.txt").readLines().map { report -> ArrayList(report.split(" ").map { it.toInt() }) }
    println(countValidReportsWithDampener(splittedReports))
}

fun countValidReportsWithDampener(splittedReports: List<ArrayList<Int>>): Int = splittedReports.map { if (isReportValidWithDampener(it)) 1 else 0 }.sum()

fun countValidReports(splittedReports: List<List<Int>>): Int = splittedReports.map { if (isReportValid(it)) 1 else 0 }.sum()

fun isReportValid(report: List<Int>): Boolean {
    val isAscending = report[0] < report[1]
    var isValid = true
    var i = 0
    while (isValid && i < report.size -1) {
        isValid = isValid && isCoupleValid(report[i], report[i +1], isAscending)
        i += 1
    }

    return isValid
}

fun isReportValidWithDampener(report: ArrayList<Int>): Boolean {
    if (!isCoupleValid(report[0], report[1], report[0] < report[1]))
        return isReportValid(report.drop(1)) || isReportValid(report.subList(0, 1) + report.subList(2, report.size))

    val isAscending = report[0] < report[1]
    var i = 0
    while (i < report.size -1) {
        if (isCoupleValid(report[i], report[i +1], isAscending))
            i += 1
        else {
            return isReportValid(report.subList(0,i) + report.subList(i+1, report.size))
                    || isReportValid(report.subList(0,i -1) + report.subList(i, report.size))
                    || isReportValid(report.subList(0,i +1) + report.subList(i+2, report.size))
        }
    }
    return true
}

fun laFlemme(report: ArrayList<Int>):Boolean {
    var isValid = isReportValid(report)
    for (i in 0..report.size -1) {
        isValid = isValid || isReportValid(report.subList(0,i) + report.subList(i+1, report.size))
    }
    return isValid
}

fun isCoupleValid(i: Int, i1: Int, isAscending: Boolean): Boolean = when (abs(i - i1)) {
        in 1..3 -> i < i1 == isAscending
        else -> false
    }
