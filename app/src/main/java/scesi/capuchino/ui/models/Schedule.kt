package scesi.capuchino.ui.models

import com.google.gson.annotations.SerializedName

// Usar @SerializedName solo cuando el nombre del json es diferente
data class Schedule(

    var day: String,
    var start: String,
    var end: String,
    var duration: Int,
    var room: String,
    var teacher: String,
    var isClass: Boolean,
)

fun Schedule.getFormattedDay(): Day? {
    return Day.entries.find { it.day == day }
}

fun Schedule.getFormattedHour(): String {
    return when(start){
        "645" -> "06:45"
        "815" -> "08:15"
        "945" -> "09:45"
        "1115" -> "11:15"
        "1245" -> "12:45"
        "1415" -> "14:15"
        "1545" -> "15:45"
        "1715" -> "17:15"
        "1845" -> "18:45"
        "2015" -> "20:15"
        "2145" -> "21:45"
        else -> "Unknown"
    }
}


enum class Day(val day: String,val dayOfWeek: Int) {
    MONDAY("LU", 0),
    TUESDAY("MA", 1),
    WEDNESDAY("MI", 2),
    THURSDAY("JU", 3),
    FRIDAY("VI", 4),
    SATURDAY("SA", 5)
}

enum class Hour(val hour:String, )