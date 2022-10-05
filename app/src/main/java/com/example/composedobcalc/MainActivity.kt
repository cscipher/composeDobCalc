package com.example.composedobcalc

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedobcalc.ui.theme.ComposeDobCalcTheme
import java.util.*

class MainActivity : ComponentActivity() {

    private var selectedDate = mutableStateOf("")
    private var ageInMinutes = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myApp()
        }
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, yearS, monthS, dayOfMonthS ->
                val newDate = "$monthS/$dayOfMonthS/$yearS"
                selectedDate.value = newDate
                ageInMinutes.value = calculateAge(selectedDate.value)
            },
            year,
            month,
            day
        ).show()

    }

    private fun calculateAge(dob: String): String {
        var calculatedAge: String;
        val sdf = java.text.SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
        val date = sdf.parse(dob)
        val dateInMin = date.time / 60000
        val currentTillTime = sdf.parse(sdf.format(System.currentTimeMillis())).time / 60000
        calculatedAge = (currentTillTime - dateInMin).toString()
        return calculatedAge
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun myApp() {
        Scaffold(
            topBar = {
                SmallTopAppBar(title = {
                    Text("Appbar")
                })
            },
            containerColor = Color(0xFFD4C2FC)
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 20.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    "Calculate your".uppercase(), style = TextStyle(
                        color = Color(0xFF28262C),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold

                    )
                )
                Box(
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .background(color = Color(0xFF83858C))
                ) {
                    Text(
                        "Age", style = TextStyle(
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(all = 10.dp)
                    )
                }
                Text(
                    "in minutes!".uppercase(), style = TextStyle(
                        color = Color(0xFF28262C),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold

                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        openDatePicker()
                    },
                    modifier = Modifier
                        .width(200.dp)
                ) {
                    Row() {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = null,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Select Date", fontSize = 15.sp)
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    selectedDate.value, style = TextStyle(
                        fontSize = 20.sp,
                    )
                )
                Text(
                    "Selected date", style = TextStyle(
                        color = Color(0xFF83858C),
                        fontSize = 13.sp,
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    ageInMinutes.value, style = TextStyle(
                        fontSize = 20.sp,
                    )
                )
                Text(
                    "Age in minutes", style = TextStyle(
                        color = Color(0xFF83858C),
                        fontSize = 13.sp,
                    )
                )

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainActivity().myApp()
}