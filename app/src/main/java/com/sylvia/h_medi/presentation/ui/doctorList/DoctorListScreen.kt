package com.sylvia.h_medi.presentation.ui.doctorList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sylvia.h_medi.presentation.ui.components.DashDoctorItem
import com.sylvia.h_medi.presentation.ui.components.SearchAppBar
import com.sylvia.h_medi.presentation.ui.theme.*

@Composable
fun DoctorListScreen(
    viewModel: DoctorListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    HMediTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {


            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    SearchAppBar(
                        searchString = viewModel.searchString.value,
                        onValueChange = {
                            viewModel.searchStringChange(it)
                        }
                    ) {
                        viewModel.handleSearch()
                    }
                }
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .padding(horizontal = 10.dp)
                ) {


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (viewModel.directToAppointment.value){
                            Text(
                                text = "Select the doctor you would like to book an appointment with",
                                style = Typography.h5,
                                color = MyBlue,
                                modifier = Modifier.padding(10.dp)
                            )
                        } else {
                            Text(
                                text = "Select to view doctor details",
                                style = Typography.h5,
                                color = MyBlue,
                                modifier = Modifier.padding(10.dp)
                            )
                        }


                        if (viewModel.specialist.value != null) {
                            Row(
                                modifier = Modifier
                                    .border(BorderStroke(1.dp, GreyText), shape = Shapes.medium)
                                    .padding(end = 10.dp)
                                    .clickable { viewModel.removeSpecialist() },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = viewModel.specialist.value!!.specialistName,
                                    color = GreyText,
                                    style = Typography.h6,
                                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
                                )

                                Icon(
                                    Icons.Filled.Close,
                                    contentDescription = "Search Icon",
                                    tint = GreyText,
                                    modifier = Modifier
                                        .width(15.dp)
                                        .height(15.dp)
                                )

                            }

                        } else {
                            Text(
                                text = "All",
                                modifier = Modifier
                                    .border(BorderStroke(1.dp, GreyText), shape = Shapes.medium)
                                    .width(50.dp)
                                    .padding(5.dp),
                                color = GreyText,
                                style = Typography.h6,
                            )
                        }
                    }




                    viewModel.doctors.value.let {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colors.background)
                        ) {

                            item {

                            }

                            items(it) { doctor ->
                                DashDoctorItem(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            end = 20.dp,
                                            start = 20.dp
                                        )
                                        .clickable {
                                            viewModel.handleDoctorClicked(doctor)
                                        },
                                    doctor = doctor
                                )
                            }
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
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

        }

    }
}



