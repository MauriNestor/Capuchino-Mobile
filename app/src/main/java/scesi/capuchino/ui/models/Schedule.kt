package scesi.capuchino.ui.models

data class Schedule(
    val day: String,
    val start: String,
    val end: String,
    val duration: Int,
    val room: String,
    val teacher: String,
    val isClass: Boolean
)

data class Group(
    val code: String,
    val teacher: String,
    val schedule: List<Schedule>
)

data class Subject(
    val code: Int,
    val name: String,
    val groups: List<Group>
)

data class Course(
    val code: String,
    val subjects: List<Subject>
)
