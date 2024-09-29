package scesi.capuchino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import scesi.capuchino.ui.theme.CalendarioScesiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalendarioScesiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalendarScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalendarScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        DaysOfWeekHeader()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f) // Ocupa solo el 40% de la pantalla
        ) {
            CalendarGrid()
        }
    }
}

@Composable
fun DaysOfWeekHeader(modifier: Modifier = Modifier) {
    val daysOfWeek = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado")

    Row(modifier = modifier.fillMaxWidth()
        .padding(top = 50.dp) ) {
        Spacer(modifier = Modifier.width(50.dp))
        daysOfWeek.forEach { day ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
            ) {
                Text(
                    text = day,
                    modifier = Modifier.align(Alignment.Center),
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }
    }
}

sealed class CalendarItem {
    data class Casilla(val dia: Int, val hora: String, val tareas: List<Tarea>) : CalendarItem()
    object Empty : CalendarItem()
}
data class Tarea(val nombre: String)



@Composable
fun CalendarGrid(modifier: Modifier = Modifier) {
    val hoursOfDay = generateSequence(6 * 60 + 45) { it + 45 }
        .takeWhile { it < 21 * 60 }
        .map { minutesToHourString(it) }
        .toList()

    val cellPadding = 4.dp
    val scrollState = rememberScrollState()

    val calendarItems = listOf(
        CalendarItem.Casilla(dia = 2, hora = "06:45", tareas = listOf(
            Tarea("base de datos G3"),
            Tarea("introduccion a la programacion  G3")
        )),
        CalendarItem.Casilla(dia = 4, hora = "15:45", tareas = listOf(
            Tarea("simulacion de sistemas G3")
        )),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(cellPadding)
    ) {
        hoursOfDay.forEach { hour ->

            Row(modifier = Modifier.fillMaxWidth().height(intrinsicSize = IntrinsicSize.Max)) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .fillMaxHeight()
                        .border(0.1.dp, Color.DarkGray)
                ) {
                    Text(
                        text = hour,
                        modifier = Modifier.align(Alignment.CenterStart),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                repeat(6) { diaIndex ->
                    val item = calendarItems.find { true && it.dia == diaIndex && it.hora == hour }
                        ?: CalendarItem.Empty

                    when (item) {
                        is CalendarItem.Casilla -> {
                            val hasCollision = item.tareas.size > 1
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(0.1.dp)
                                    .border(1.dp, Color.Black)
                                    .background(if (hasCollision) Color.Red else Color.Green),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(modifier = Modifier.padding(1.dp)) {
                                    item.tareas.forEach { tarea ->
                                        Text(
                                            text = tarea.nombre,
                                            fontSize = 9.sp,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }

                        is CalendarItem.Empty -> {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .border(0.1.dp, Color.DarkGray)
                            ) {
                            }
                        }
                    }
                }
            }
        }
    }
}

fun minutesToHourString(minutes: Int): String {
    val hours = minutes / 60
    val mins = minutes % 60
    return String.format("%02d:%02d", hours, mins)
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CalendarioScesiTheme {
        CalendarScreen()
    }
}
