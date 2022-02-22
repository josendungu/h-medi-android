package com.sylvia.h_medi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.presentation.ui.bookAppointment.AppointmentDetailScreen
import com.sylvia.h_medi.presentation.ui.appointmentList.AppointmentListScreen
import com.sylvia.h_medi.presentation.ui.doctorDetail.DoctorDetailScreen
import com.sylvia.h_medi.presentation.ui.doctorList.DoctorListScreen
import com.sylvia.h_medi.presentation.ui.home.HomeScreen
import com.sylvia.h_medi.presentation.ui.login.LoginScreen
import com.sylvia.h_medi.presentation.ui.register.RegisterScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            LaunchedEffect(key1 = "navigation"){
                navigator.sharedFlow.onEach {
                    navController.navigate(it.route)
                }.launchIn(this)
            }

            NavHost(
                navController = navController,
                startDestination = Screen.LoginScreen.route
            ) {

                composable(
                    route = Screen.HomeScreen.route
                ) {
                    HomeScreen()
                }

                composable(
                    route = Screen.LoginScreen.route
                ) {
                    LoginScreen(navController = navController)
                }

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
