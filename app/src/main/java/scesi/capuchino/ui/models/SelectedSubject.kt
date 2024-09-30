package scesi.capuchino.ui.models

data class SelectedSubject (
    var codeGroup: String,
    var teacher: String,
    var codeSubject: String,
    var name: String,
    var schedule: ArrayList<Schedule> = arrayListOf(),
)

fun SelectedSubject.isInDay(dayOfWeek: Int): Boolean {
    return this.schedule.any { it.getFormattedDay()?.dayOfWeek == dayOfWeek }
}

fun SelectedSubject.isInHour(hour: String): Boolean{
    return this.schedule.any { it.getFormattedHour() == hour}
}