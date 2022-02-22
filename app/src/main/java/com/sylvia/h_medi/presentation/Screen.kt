package com.sylvia.h_medi.presentation

sealed class Screen(val route: String) {

    object RegisterScreen: Screen("register_screen")
    object DoctorListScreen: Screen("doctor_list_screen")
    object DoctorDetailScreen: Screen("doctor_detail_screen")
    object AppointmentListScreen: Screen("appointment_list_screen")
    object AppointmentDetailScreen: Screen("appointment_detail_screen")
    object HomeScreen: Screen("home_screen")
    object LoginScreen: Screen("login_screen")


}