package scesi.capuchino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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

    Row(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(50.dp)) // Espacio para la columna de horas
        daysOfWeek.forEach { day ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
            ) {
                Text(
                    text = day,
                    modifier = Modifier.align(Alignment.Center),
                    style = TextStyle(fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

sealed class CalendarItem {
    data class Tarea(val dia: Int, val hora: String, val nombre: String) : CalendarItem()
    object Empty : CalendarItem()
}


@Composable
fun CalendarGrid(modifier: Modifier = Modifier) {
    val hoursOfDay = (9..21).map { "$it:00" }
    val cellPadding = 4.dp

    val scrollState = rememberScrollState()

    val calendarItems = listOf(
        CalendarItem.Tarea(dia = 2, hora = "10:00", nombre = "Boris Calancha G3"),
        CalendarItem.Tarea(dia = 2, hora = "10:00", nombre = "Leticia Coca G3"),
        CalendarItem.Tarea(dia = 4, hora = "15:00", nombre = "Henry tapia perro G3"),

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
                        .padding(cellPadding)
                ) {
                    Text(
                        text = hour,
                        modifier = Modifier.align(Alignment.CenterStart),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                repeat(6) { diaIndex ->
                    val item = calendarItems.find { it is CalendarItem.Tarea && it.dia == diaIndex && it.hora == hour }
                        ?: CalendarItem.Empty

                    when (item) {
                        is CalendarItem.Tarea -> {
                            // Celda con tarea
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(cellPadding)
                                    .background(Color.Green),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.nombre,
                                    fontSize = 9.sp,
                                    color = Color.Black
                                )
                            }
                        }

                        is CalendarItem.Empty -> {
                            // Celda vacía
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(cellPadding)
                                    .background(MaterialTheme.colorScheme.surfaceVariant),
                                contentAlignment = Alignment.Center
                            ) {
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CalendarioScesiTheme {
        CalendarScreen()
    }
}
