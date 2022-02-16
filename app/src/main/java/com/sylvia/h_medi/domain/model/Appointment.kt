package com.sylvia.h_medi.domain.model


data class Appointment(
    val time: String,
    val date: Int,
    val doctor: Doctor,
    val appointment_id: Int
)
