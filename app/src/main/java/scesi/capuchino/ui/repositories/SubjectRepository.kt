package scesi.capuchino.ui.repositories

import com.google.gson.Gson
import scesi.capuchino.ui.models.Subject

object SubjectRepository {
    // Ideal
    // UI(compose) ->  viewmodel -> use case ->  repository ->  datasource ->  API/DB
    // actual
    // compose ->  repository
    fun getSubjects(): List<Subject> {
        // Gson siempre convierte a la clase que le pases
        return Gson().fromJson(subjectJsonList, Array<Subject>::class.java).toList()
    }
}

// Formas de declarar strings
val st = "st"
val json = "string"
val json2 = "string" + "string2"
val json3 = "string $st"
val json4 = "string" + st
val json5 = "string line 1" +
        "line 2 \n" +
        "line 3 \n"

const val subjectJsonList = """
      [
        {
          "code": "1",
          "teacher": "CESPEDES GUIZADA MARIA BENITA",
          "schedule": [
            {
              "day": "MA",
              "start": "815",
              "end": "945",
              "duration": 2,
              "room": "693B",
              "teacher": "CESPEDES GUIZADA MARIA BENITA",
              "isClass": true
            },
            {
              "day": "VI",
              "start": "815",
              "end": "945",
              "duration": 2,
              "room": "691D",
              "teacher": "CESPEDES GUIZADA MARIA BENITA",
              "isClass": true
            }
          ]
        },
        {
          "code": "2",
          "teacher": "BLANCO COCA LETICIA",
          "schedule": [
            {
              "day": "MA",
              "start": "1715",
              "end": "1845",
              "duration": 2,
              "room": "617",
              "teacher": "BLANCO COCA LETICIA",
              "isClass": true
            },
            {
              "day": "MI",
              "start": "1715",
              "end": "1845",
              "duration": 2,
              "room": "691B",
              "teacher": "VELIZ ESCOBAR JOSUE DEMETRIO",
              "isClass": true
            },
            {
              "day": "JU",
              "start": "1545",
              "end": "1715",
              "duration": 2,
              "room": "624",
              "teacher": "BLANCO COCA LETICIA",
              "isClass": true
            }
          ]
        }
      ]
"""