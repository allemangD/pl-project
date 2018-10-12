import java.io.File


fun main(args: Array<String>) {
    // File I/O is easily done with `File()` constructor
    val file = File("res/sample.grades")

    // Maps and Lists are, by default, immutable.
    // Specify our data structure to be mutable at both levels
    val courses = mutableMapOf<String, MutableList<Int>>()

    // File supports line iteration with forEachLine, which accepts a code block.
    // `it` is the iteration variable in the code block
    file.forEachLine {
        // It is possible to unpack iterables into multiple variables
        // This is very brittle, though, if improperly formatted
        val (rawCourse, rawGrade) = it.split(":")

        // use `trim()` to strip whitespace from either side of the strings
        // string conversion is easy with helper methods like `toInt()` and `toIntOrNull()`
        val course = rawCourse.trim()
        val grade = rawGrade.trim().toInt()

        // collection testing is easy with `in` and `!in` operators
        if (course !in courses)
            courses[course] = mutableListOf()  // implicit type arguments are passed: `mutableListOf<Int>()`

        // use `!!` to assert the hash lookup will not be null. We just added it to the map.
        courses[course]!!.add(grade)
    }


    // Iteration over maps is easy when combined with iterable unpacking
    for ((course, grades) in courses) {
        val average = grades.average()  // List<Int> has helper methods such as `sum()` and `average()`

        // use a `when` block as an alternative to case. Supports rich pattern matching,
        // but we just use it here to check which grade range we're in
        val letter = when {
            average > 90 -> "A"
            average > 80 -> "B"
            average > 70 -> "C"
            average > 70 -> "D"
            else -> "F"
        }

        // input and output from console are as easy as `println()` and `readline()`
        // uses string interpolation for easier formatting
        println("$course:\n\t$letter ($average)\n")
    }
}