package com.sylvia.h_medi.presentation.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sylvia.h_medi.presentation.ui.appointmentList.AppointmentListScreen
import com.sylvia.h_medi.presentation.ui.dashboard.DashboardScreen
import com.sylvia.h_medi.presentation.ui.emergency.EmergencyScreen
import com.sylvia.h_medi.presentation.ui.home.customBottomNav.BottomNavScreen
import com.sylvia.h_medi.presentation.ui.home.customBottomNav.CustomBottomNavigation
import com.sylvia.h_medi.presentation.ui.profile.ProfileScreen
import com.sylvia.h_medi.presentation.ui.theme.GreyText
import com.sylvia.h_medi.presentation.ui.theme.HMediTheme
import com.sylvia.h_medi.presentation.ui.theme.Typography

@Composable
fun HomeScreen() {

    val currentScreen = remember{mutableStateOf<BottomNavScreen>(BottomNavScreen.Dashboard)}
    
    HMediTheme {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                CustomBottomNavigation(currentScreenRoute = currentScreen.value.route){
                    currentScreen.value =  it
                }
            },
        ) {

            when(currentScreen.value) {
                is BottomNavScreen.Dashboard -> {
                    DashboardScreen()
                }
                is BottomNavScreen.AppointmentList -> {
                    AppointmentListScreen()
                }
                is BottomNavScreen.Emergency -> {
                    EmergencyScreen()
                }
                is BottomNavScreen.Profile -> {
                    ProfileScreen()
                }
            }

        }
        
    }
}

