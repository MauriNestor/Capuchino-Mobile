package scesi.capuchino.ui.repositories

import com.google.gson.Gson
import scesi.capuchino.ui.mockedData.selectedSubjects
import scesi.capuchino.ui.models.Group
import scesi.capuchino.ui.models.SelectedSubject
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

    // Is for getting selected subjects , different structure than above method
    fun getSelectedSubjects(): List<SelectedSubject> {
        return Gson().fromJson(selectedSubjects, Array<SelectedSubject>::class.java).toList()
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
          },
          {
            "code": "3",
            "teacher": "PEETERS ILONAA MAGDA LENA",
            "schedule": [
              {
                "day": "LU",
                "start": "645",
                "end": "815",
                "duration": 2,
                "room": "691B",
                "teacher": "PEETERS ILONAA MAGDA LENA",
                "isClass": true
              },
              {
                "day": "MI",
                "start": "645",
                "end": "815",
                "duration": 2,
                "room": "692H",
                "teacher": "PEETERS ILONAA MAGDA LENA",
                "isClass": true
              }
            ]
          },
          {
            "code": "4",
            "teacher": "GRILO SALVATIERRA MARIA ESTELA",
            "schedule": [
              {
                "day": "MA",
                "start": "1545",
                "end": "1715",
                "duration": 2,
                "room": "692G",
                "teacher": "GRILO SALVATIERRA MARIA ESTELA",
                "isClass": true
              },
              {
                "day": "VI",
                "start": "1415",
                "end": "1545",
                "duration": 2,
                "room": "692E",
                "teacher": "GRILO SALVATIERRA MARIA ESTELA",
                "isClass": true
              }
            ]
          },
          {
            "code": "5",
            "teacher": "CESPEDES GUIZADA MARIA BENITA",
            "schedule": [
              {
                "day": "JU",
                "start": "945",
                "end": "1115",
                "duration": 2,
                "room": "692F",
                "teacher": "CESPEDES GUIZADA MARIA BENITA",
                "isClass": true
              },
              {
                "day": "VI",
                "start": "1115",
                "end": "1245",
                "duration": 2,
                "room": "691B",
                "teacher": "CESPEDES GUIZADA MARIA BENITA",
                "isClass": true
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
                "duration": 2,
                "room": "612",
                "teacher": "VALENZUELA MIRANDA ROBERTO",
                "isClass": true
              },
              {
                "day": "MI",
                "start": "1415",
                "end": "1545",
                "duration": 2,
                "room": "692C",
                "teacher": "VALENZUELA MIRANDA ROBERTO",
                "isClass": true
              }
            ]
          },
          {
            "code": "B1",
            "teacher": "MOREIRA CALIZAYA RENE",
            "schedule": [
              {
                "day": "JU",
                "start": "645",
                "end": "815",
                "duration": 2,
                "room": "621",
                "teacher": "MOREIRA CALIZAYA RENE",
                "isClass": true
              }
            ]
          },
          {
            "code": "B2",
            "teacher": "RUIZ UCUMARI IVAN",
            "schedule": [
              {
                "day": "LU",
                "start": "945",
                "end": "1115",
                "duration": 2,
                "room": "620",
                "teacher": "RUIZ UCUMARI IVAN",
                "isClass": true
              }
            ]
          },
          {
            "code": "B3",
            "teacher": "ORDONEZ SALVATIERRA MIGUEL ANGEL",
            "schedule": [
              {
                "day": "JU",
                "start": "1245",
                "end": "1415",
                "duration": 2,
                "room": "620",
                "teacher": "ORDONEZ SALVATIERRA MIGUEL ANGEL",
                "isClass": true
              }
            ]
          },
          {
            "code": "B4",
            "teacher": "RUIZ UCUMARI IVAN",
            "schedule": [
              {
                "day": "MA",
                "start": "945",
                "end": "1115",
                "duration": 2,
                "room": "621",
                "teacher": "RUIZ UCUMARI IVAN",
                "isClass": true
              }
            ]
          },
          {
            "code": "B5",
            "teacher": "TERRAZAS VARGAS JUAN CARLOS",
            "schedule": [
              {
                "day": "MI",
                "start": "1115",
                "end": "1245",
                "duration": 2,
                "room": "620",
                "teacher": "TERRAZAS VARGAS JUAN CARLOS",
                "isClass": true
              }
            ]
          },
          {
            "code": "B6",
            "teacher": "TERRAZAS VARGAS JUAN CARLOS",
            "schedule": [
              {
                "day": "JU",
                "start": "1115",
                "end": "1245",
                "duration": 2,
                "room": "620",
                "teacher": "TERRAZAS VARGAS JUAN CARLOS",
                "isClass": true
              }
            ]
          }
        ]
      }
    ]
"""