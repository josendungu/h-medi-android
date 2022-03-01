package com.sylvia.h_medi.presentation.ui.bookAppointment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sylvia.h_medi.common.Constants.TAG
import com.sylvia.h_medi.common.utils.DateUtils
import com.sylvia.h_medi.presentation.ui.components.DashDoctorItem
import com.sylvia.h_medi.presentation.ui.theme.HMediTheme
import com.sylvia.h_medi.presentation.ui.theme.MyBlue
import com.sylvia.h_medi.presentation.ui.theme.Shapes
import com.sylvia.h_medi.presentation.ui.theme.Typography
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


@Composable
fun AppointmentDetailScreen(
    viewModel: BookAppointmentViewModel = hiltViewModel(),
    context: Context
) {

    val state = viewModel.state.value



    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val initHour = calendar.get(Calendar.HOUR)
    val initMinute = calendar.get(Calendar.MINUTE)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, theYear: Int, theMonth: Int, dayOfMonth: Int ->
            val date = LocalDate.of(theYear, theMonth, dayOfMonth)
            viewModel.setDate.value = date
        }, year, month, day
    )


    val timePickerDialog = TimePickerDialog(
        context,
        {_, hour : Int, minute: Int ->
            val time = LocalTime.of(hour, minute)
            viewModel.setTime.value = time
        }, initHour, initMinute, false

    )

    HMediTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {


            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {


                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {

                        Text(
                            text = if (!viewModel.isUpdate.value) {
                                "Book Appointment"
                            } else {
                                "Update Appointment"
                            },
                            modifier = Modifier
                                .padding(start = 40.dp),
                            style = Typography.h4
                        )
                    }


                    DashDoctorItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        doctor = viewModel.selectedDoctor.value!!
                    )


                    if (state.bookingSuccess){
                        Icon(
                            Icons.Outlined.Check,
                            contentDescription = "Search Icon",
                            tint = Color.Green,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(CenterHorizontally)
                                .width(70.dp)
                                .height(70.dp)
                        )


                        Text(
                            text = if (!viewModel.isUpdate.value) {
                                "Appointment was successfully booked"
                            } else {
                                "Appointment was successfully updated "
                            },
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth()
                                .align(CenterHorizontally),
                            style = Typography.h4,
                            color = Color.Green,
                            textAlign = TextAlign.Center
                        )

                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Column(
                            modifier = Modifier
                                .border(BorderStroke(1.dp, MyBlue), shape = Shapes.small)
                                .padding(horizontal = 10.dp, vertical = 30.dp)
                                .width(140.dp)
                        ) {
                            Text(
                                text = "Time",
                                style = Typography.h4
                            )

                            Text(
                                text = if (viewModel.setTime.value != null) {
                                    DateUtils.timeToString(viewModel.setTime.value!!)
                                } else {
                                    "Time is not yet set"
                                },
                                style = Typography.h5
                            )
                        }

                        Column(
                            modifier = Modifier
                                .border(BorderStroke(1.dp, MyBlue), shape = Shapes.small)
                                .padding(horizontal = 10.dp, vertical = 30.dp)
                                .width(140.dp)
                        ) {
                            Text(
                                text = "Date",
                                style = Typography.h4
                            )

                            Text(
                                text = if (viewModel.setDate.value != null) {
                                    DateUtils.dateToString(viewModel.setDate.value!!)
                                } else {
                                    "Date is not yet set"
                                },
                                style = Typography.h5
                            )
                        }

                    }

                    Spacer(modifier = Modifier.padding(20.dp))

                    if (!state.bookingSuccess){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { timePickerDialog.show() },
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(150.dp)
                            ) {
                                Text(
                                    text = "Set Time",
                                    style = Typography.h4
                                )
                            }

                            Button(
                                onClick = { datePickerDialog.show()},
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(150.dp)
                            ) {
                                Text(
                                    text = "Set Date",
                                    style = Typography.h4
                                )
                            }
                        }

                        Spacer(modifier = Modifier.padding(30.dp))

                        Button(
                            onClick = { viewModel.handleButtonClicked()},
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = if (!viewModel.isUpdate.value) {
                                    "Book Appointment"
                                } else {
                                    "Update Appointment"
                                },
                                style = Typography.h4
                            )
                        }
                    } else {
                        Button(
                            onClick = { viewModel.navigateToHome()},
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = "Done",
                                style = Typography.h4
                            )
                        }
                    }

                }

            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            if (state.validateError.isNotBlank()) {
                Snackbar(
                    modifier = Modifier.padding(8.dp)
                ) { Text(text = state.validateError) }
            }


        }


    }

}