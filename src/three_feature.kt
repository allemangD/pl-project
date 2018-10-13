import java.io.File


class ReportCard {
    // Maps and Lists are, by default, immutable.
    // Specify our data structure to be mutable at both levels
    private val courses = mutableMapOf<String, MutableList<Int>>()

    // use getOrPut to get a list of course grades, and add the new grade to that list
    fun addAssignment(course: String, grade: Int) = courses.getOrPut(course) { mutableListOf() }.add(grade)

    fun grades(func: (String, String, Double) -> Unit) {
        // Iteration over maps is easy when combined with iterable unpacking
        for ((course, grades) in courses) {
            val average = grades.average()  // List<Int> has helper methods such as `sum()` and `average()`

            // use a `when` block as an alternative to case. Supports rich pattern matching,
            // but we just use it here to check which grade range we're in
            val letter = when {
                average > 90 -> "A"
                average > 80 -> "B"
                average > 70 -> "C"
                average > 60 -> "D"
                else -> "F"
            }

            // call back to the block given
            func(course, letter, average)
        }
    }
}

// Create an extension method on `Double` that allows us to specify precision
fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

fun main(args: Array<String>) {
    // File I/O is easily done with `File()` constructor
    val file = File("res/sample.grades")

    val report = ReportCard()

    // `File` supports line iteration with `forEachLine`, which accepts a code block.
    // `it` is the iteration variable in the code block
    file.forEachLine {
        // It is possible to unpack iterables into multiple variables
        // This is very brittle, though, if improperly formatted
        val (rawCourse, rawGrade) = it.split(":").let {
            Pair(it[0], it.getOrElse(1) { _ -> "" })
        }

        // use `trim()` to strip whitespace from either side of the strings
        // string conversion is easy with helper methods like `toInt()` and `toIntOrNull()`
        val course = rawCourse.trim()
        val grade = rawGrade.trim().toIntOrNull()
        grade?.let { report.addAssignment(course, it) }
    }

    // input and output from console are as easy as `println()` and `readline()`
    // uses string interpolation for easier formatting
    // grade precision is specified with the `format` extension method defined above
    report.grades { course: String, letter: String, average: Double ->
        println("$course:\n\t$letter (${average.format(2)})\n")
    }
}