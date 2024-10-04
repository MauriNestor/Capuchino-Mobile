package scesi.capuchino.ui.repositories

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import scesi.capuchino.ui.models.Course
import scesi.capuchino.ui.models.SelectedSubject
import scesi.capuchino.ui.models.Schedule

class ScheduleRepository {

    private val jsonSource = """
    {
      "levels": [
        {
          "code": "A",
          "subjects": [
            {
              "code": 1803001,
              "name": "INGLES I",
              "groups": [
                {
                  "code": "1",
                  "teacher": "CESPEDES GUIZADA MARIA BENITA",
                  "schedule": [
                    {
                      "day": "MA",
                      "start": "815",
                      "end": "945",
                      "room": "693B"
                    },
                    {
                      "day": "VI",
                      "start": "815",
                      "end": "945",
                      "room": "691D"
                    }
                  ]
                }
              ]
            },
            {
              "code": 2006063,
              "name": "FISICA GENERAL",
              "groups": [
                {
                  "code": "B",
                  "teacher": "VALENZUELA MIRANDA ROBERTO",
                  "schedule": [
                    {
                      "day": "MA",
                      "start": "1115",
                      "end": "1245",
                      "room": "612"
                    },
                    {
                      "day": "MI",
                      "start": "1415",
                      "end": "1545",
                      "room": "692C"
                    }
                  ]
                }
              ]
            }
          ]
        }
      ]
    }
    """

    fun loadCourse(): Course {
        val courseType = object : TypeToken<Course>() {}.type
        return Gson().fromJson(jsonSource, courseType)
    }

    fun getSelectedSubjects(): List<SelectedSubject> {
        val course = loadCourse()

        return course.levels.flatMap { level ->
            level.subjects.flatMap { subject ->
                subject.groups.map { group ->
                    SelectedSubject(
                        codeGroup = group.code,
                        teacher = group.teacher,
                        codeSubject = subject.code.toString(),
                        name = subject.name,
                        schedule = group.schedule.map {
                            Schedule(
                                day = it.day,
                                start = normalizeHour(it.start),
                                end = normalizeHour(it.end),
                                room = it.room
                            )
                        }
                    )
                }
            }
        }
    }

    private fun normalizeHour(hour: String): String {
        return hour.padStart(4, '0') // Asegura que siempre tenga 4 caracteres, añadiendo '0' si es necesario
    }
}