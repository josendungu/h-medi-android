package com.sylvia.h_medi.domain.model

import java.util.*


data class Appointment(
    val time: String,
    val date: Date,
    val doctor: Doctor,
    val appointmentId: Int,
    val specialist: String
)
