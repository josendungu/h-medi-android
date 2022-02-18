package com.sylvia.h_medi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sylvia.h_medi.presentation.ui.appointmentDetail.AppointmentDetailScreen
import com.sylvia.h_medi.presentation.ui.appointmentList.AppointmentListScreen
import com.sylvia.h_medi.presentation.ui.doctorDetail.DoctorDetailScreen
import com.sylvia.h_medi.presentation.ui.doctorList.DoctorListScreen
import com.sylvia.h_medi.presentation.ui.register.RegisterScreen
import com.sylvia.h_medi.presentation.ui.theme.HMediTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.DoctorListScreen.route
            ) {

                composable(
                    route = Screen.RegisterScreen.route
                ) {
                    RegisterScreen()
                }

                composable(
                    route = Screen.DoctorListScreen.route
                ) {
                    DoctorListScreen()
                }

                composable(
                    route = Screen.DoctorDetailScreen.route
                ) {
                    DoctorDetailScreen()
                }

                composable(
                    route = Screen.AppointmentListScreen.route
                ) {
                    AppointmentListScreen()
                }

                composable(
                    route = Screen.AppointmentDetailScreen.route
                ) {
                    AppointmentDetailScreen()
                }

            }
        }
    }
}
