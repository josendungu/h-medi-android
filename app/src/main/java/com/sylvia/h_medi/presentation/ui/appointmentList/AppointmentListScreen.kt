package com.sylvia.h_medi.presentation.ui.appointmentList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sylvia.h_medi.R
import com.sylvia.h_medi.presentation.ui.components.AppointmentItem
import com.sylvia.h_medi.presentation.ui.theme.HMediTheme
import com.sylvia.h_medi.presentation.ui.theme.MyBlue
import com.sylvia.h_medi.presentation.ui.theme.Typography

@Composable
fun AppointmentListScreen(
    viewModel: AppointmentListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    HMediTheme {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.navigateToAddAppointment() },
                backgroundColor = MyBlue,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                modifier = Modifier.padding(bottom = 60.dp)
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ){


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {


                Text(
                    text = "Your Appointments",
                    style = Typography.body2,
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 70.dp)
                ){

                    items(state.appointments) { appointment ->
                        AppointmentItem(appointment){
                            viewModel.navigateToAppointmentDetail(it)
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

}