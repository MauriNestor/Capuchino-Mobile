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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import scesi.capuchino.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
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

// Clase tarea
data class Tarea(
    val dia: Int,
    val hora: String,
    val nombre: String
)

@Composable
fun CalendarGrid(modifier: Modifier = Modifier) {
    val hoursOfDay = (9..21).map { "$it:00" }
    val cellPadding = 4.dp

    val scrollState = rememberScrollState()

    val density = LocalDensity.current

    val tareas = listOf(
        Tarea(dia = 2, hora = "10:00", nombre = "Boris Calancha G3") // Tarea en Miércoles a las 10:00
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(cellPadding)
    ) {
        hoursOfDay.forEach { hour ->
            var rowHeight by remember { mutableStateOf(50.dp) }

            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(rowHeight)
                        .padding(cellPadding)
                ) {
                    Text(
                        text = hour,
                        modifier = Modifier.align(Alignment.CenterStart),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                // Celdas para los días
                repeat(6) { diaIndex ->
                    val tarea = tareas.find { it.dia == diaIndex && it.hora == hour }

                    if (tarea != null) {
                        // Celda con tarea
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(cellPadding)
                                .background(Color.Green)
                                .onGloballyPositioned { coordinates ->
                                    val taskHeight = with(density) { coordinates.size.height.toDp() }

                                    if (taskHeight > rowHeight) {
                                        rowHeight = taskHeight
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tarea.nombre,
                                fontSize = 9.sp,
                                color = Color.Black
                            )
                        }
                    } else {
                        // Celda vacía
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(rowHeight)
                                .aspectRatio(1f)
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

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    ComposeTheme {
        CalendarScreen()
    }
}
