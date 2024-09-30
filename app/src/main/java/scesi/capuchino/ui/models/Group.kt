package scesi.capuchino.ui.models

data class Group(
    var code: String,
    var teacher: String,
    var schedule: ArrayList<Schedule> = arrayListOf(),
)


fun Group.isInDay(dayOfWeek: Int): Boolean {
    return this.schedule.any { it.getFormattedDay()?.dayOfWeek == dayOfWeek }
}

fun Group.isInHour(hour: String): Boolean{
    return this.schedule.any { it.getFormattedHour() == hour}
}