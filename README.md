# Term Project Part I

## Three Programs

Create a program (or programs) which show how the language you picked (Kotlin) works with the following:

1. I/O
1. Data Structures
1. Control Structures

## Report Card Generator

Accepts a file `sample.grades` where each line is an assignment in the format: `<course name>: <grade>`. The program iterates through the file and computes the average and letter grades for all courses listed in `sample.grades`.

### File links
[`sample.grades`](https://github.com/allemangD/pl-project/blob/master/res/sample.grades)  
[`three_feature.kt`](https://github.com/allemangD/pl-project/blob/master/src/three_feature.kt)

### Language features

Language structures used include:

* Iterable unpacking
* Implicit type arguments
* Null safety and propagation (as in `courses[course]!!.add(grade)`)
* Iteration
* Actions (as in `file.forEachLine`)
* Pattern matching (simple example using `when` expression)
* String interpolation

Kotlin library features used include:

* File I/O
* Standard I/O
* String conversions
* Mutable maps and lists
