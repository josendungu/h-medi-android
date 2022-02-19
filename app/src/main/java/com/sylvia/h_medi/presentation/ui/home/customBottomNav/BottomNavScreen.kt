package com.sylvia.h_medi.presentation.ui.home.customBottomNav

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.content.ContextCompat
import com.sylvia.h_medi.R

sealed class BottomNavScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Dashboard: BottomNavScreen("dashboard", "Home", Icons.Outlined.Home)
    object AppointmentList: BottomNavScreen("appointment_list", "Appointments", Icons.Outlined.List)
    object Emergency: BottomNavScreen("emergency", "Emergency", Icons.Outlined.Notifications)
    object Profile: BottomNavScreen("profile", "Profile", Icons.Outlined.Person)

    object Items{
        val list = listOf(Dashboard, AppointmentList, Emergency, Profile)
    }

}
