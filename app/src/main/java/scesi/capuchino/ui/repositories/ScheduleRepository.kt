package scesi.capuchino.ui.repositories

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import scesi.capuchino.ui.models.Course
import scesi.capuchino.ui.models.Schedule

open class ScheduleRepository(private val jsonSource: String? = null) {

    private val defaultJson = """
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
                  "start": "0815",
                  "end": "0945",
                  "duration": 2,
                  "room": "693B",
                  "teacher": "CESPEDES GUIZADA MARIA BENITA",
                  "isClass": true
                },
                {
                  "day": "VI",
                  "start": "0815",
                  "end": "0945",
                  "duration": 2,
                  "room": "691D",
                  "teacher": "CESPEDES GUIZADA MARIA BENITA",
                  "isClass": true
                }
           
              ]
            }, 
            {
              "code": "2",
              "teacher": "CESPEDES GUIZADA MARIA BENITA",
              "schedule": [
                {
                  "day": "MA",
                  "start": "1115",
                  "end": "1245",
                  "duration": 2,
                  "room": "690D",
                  "teacher": "CESPEDES GUIZADA MARIA BENITA",
                  "isClass": true
                },
                {
                  "day": "VI",
                  "start": "945",
                  "end": "1115",
                  "duration": 2,
                  "room": "690D",
                  "teacher": "CESPEDES GUIZADA MARIA BENITA",
                  "isClass": true
                }
              ]
            }
          ]
        }
      ]
    }
    """

    open fun loadCourse(): Course {
        val jsonToParse = jsonSource ?: defaultJson
        val courseType = object : TypeToken<Course>() {}.type
        return Gson().fromJson(jsonToParse, courseType)
    }

    fun getAllSchedules(): List<Schedule> {
        val course = loadCourse()
        val schedules = course.subjects
            .flatMap { it.groups }
            .flatMap { it.schedule }

        return schedules
    }

}

