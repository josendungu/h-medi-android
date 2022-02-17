package com.sylvia.h_medi.presentation

sealed class Screen(val route: String) {

    object RegisterScreen: Screen("register_screen")
    object DoctorListScreen: Screen("doctor_list_screen")

}