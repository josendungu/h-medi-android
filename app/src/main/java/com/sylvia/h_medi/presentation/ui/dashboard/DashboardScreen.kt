package com.sylvia.h_medi.presentation.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sylvia.h_medi.presentation.ui.components.DashDoctorItem
import com.sylvia.h_medi.presentation.ui.components.SpecialistItem
import com.sylvia.h_medi.presentation.ui.theme.GreyText
import com.sylvia.h_medi.presentation.ui.theme.Typography

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val dashDoctorsState = viewModel.stateDashDoctors.value
    val topDoctorsState = viewModel.stateTopDoctors.value
    val specialistState = viewModel.stateSpecialists.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
        ) {
            Text(
                text = "Welcome",
                color = GreyText,
                style = Typography.h6,
                modifier = Modifier.padding(2.dp)
            )
            Text(
                text = state.patientFirstName,
                style = Typography.h3,
                modifier = Modifier.padding(2.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {

            item {

                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ){

                    itemsIndexed(
                        dashDoctorsState.doctors
                    ){ index, doctor  ->


                        if (index == 0){
                            DashDoctorItem(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .padding(
                                        start = 20.dp,
                                        end = 10.dp,
                                        top = 10.dp,
                                        bottom = 10.dp
                                    )
                                    .clickable { viewModel.navigateToDoctorDetails(doctor.doctorId) },
                                doctor = doctor
                            )

                        } else {
                            DashDoctorItem(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .padding(vertical = 10.dp, horizontal = 10.dp)
                                    .clickable { viewModel.navigateToDoctorDetails(doctor.doctorId) },
                                doctor = doctor
                            )
                        }

                    }

                }

                Text(
                    text = "Find a doctor specialist",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    style = Typography.h5
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ){

                    itemsIndexed(
                        specialistState.specialists
                    ){ index, specialist  ->

                        if (index == 0){
                            SpecialistItem(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .padding(
                                        start = 30.dp,
                                        end = 10.dp,
                                        top = 10.dp,
                                        bottom = 10.dp
                                    )
                                    .clickable { },
                                specialist = specialist
                            )

                        } else {
                            SpecialistItem(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .padding(10.dp)
                                    .clickable { },
                                specialist = specialist
                            )
                        }

                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .clickable {
                            viewModel.navigateToDoctorList(null)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Top Doctors",
                        style = Typography.h4
                    )
                    Text(
                        text = "See All",
                        style = Typography.h6,
                        modifier = Modifier
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 60.dp)
                ){

                    topDoctorsState.doctors.forEach {
                        DashDoctorItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp, end = 20.dp, start = 20.dp),
                            doctor = it)
                    }

                }

            }

        }
    }



}