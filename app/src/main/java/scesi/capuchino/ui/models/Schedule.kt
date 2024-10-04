package scesi.capuchino.ui.models

data class Schedule(
    val day: String,
    val start: String,
    val end: String,
    val room: String
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

data class Level(
    val code: String,
    val subjects: List<Subject>
)

data class Course(
    val levels: List<Level>
)

data class SelectedSubject(
    var codeGroup: String,
    var teacher: String,
    var codeSubject: String,
    var name: String,
    var schedule: List<Schedule> = listOf()
)