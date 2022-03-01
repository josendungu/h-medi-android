package com.sylvia.h_medi.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sylvia.h_medi.common.utils.DateUtils
import com.sylvia.h_medi.domain.model.Appointment
import com.sylvia.h_medi.presentation.ui.theme.*


@Composable
fun AppointmentItem(
    appointment: Appointment,
    onClicked: (appointmentId: Int) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .clickable { onClicked(appointment.appointmentId) },
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.White,
        elevation = 7.dp,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Column{

                    Text(
                        text = "Date",
                        color = GreyText,
                        style = Typography.h6,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = DateUtils.dateToString(appointment.date),
                        style = Typography.body2,
                        modifier = Modifier.padding(2.dp)
                    )

                }

                Column {

                    Text(
                        text = "Time",
                        color = GreyText,
                        style = Typography.h6,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = DateUtils.timeToString(appointment.time),
                        style = Typography.body2,
                        modifier = Modifier.padding(2.dp)
                    )

                }

                Column {

                    Text(
                        text = "Doctor",
                        color = GreyText,
                        style = Typography.h6,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = "Dr. ${appointment.doctor.firstName}",
                        style = Typography.body2,
                        modifier = Modifier.padding(2.dp)
                    )

                }

            }

            Divider(color = GreyLine, thickness = 1.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Appointment Type",
                        color = GreyText,
                        modifier = Modifier.padding(2.dp),
                        style = Typography.h6,
                    )
                    Text(
                        text = appointment.specialist,
                        style = Typography.body2,
                        modifier = Modifier.padding(2.dp)
                    )

                }

                Button(
                    onClick = { onClicked(appointment.appointmentId) },
                    modifier = Modifier
                        .background(MyBlue)
                        .height(35.dp),
                    shape = Shapes.small
                ) {
                    Text(text = "Reschedule")
                }
            }
        }
    }
}
