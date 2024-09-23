package scesi.capuchino.ui.models

data class Subject(

    var code: String,
    var teacher: String,
    var schedule: ArrayList<Schedule> = arrayListOf(),
)


fun Subject.isInDay(dayOfWeek: Int): Boolean {
    return this.schedule.any { it.getFormattedDay()?.dayOfWeek == dayOfWeek }
}

fun Subject.isInHour(hour: String): Boolean{
    return this.schedule.any { it.getFormattedHour() == hour}
}