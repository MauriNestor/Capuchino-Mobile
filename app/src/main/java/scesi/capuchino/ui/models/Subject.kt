package scesi.capuchino.ui.models

class Subject(
    var code: String,
    var name: String,
    var groups: ArrayList<Group> = arrayListOf(),
)

fun Subject.isInDay(dayOfWeek: Int): Boolean {
    return this.groups.any { it.isInDay(dayOfWeek) }
}

fun Subject.isInHour(hour: String): Boolean {
    return this.groups.any { it.isInHour(hour) }
}