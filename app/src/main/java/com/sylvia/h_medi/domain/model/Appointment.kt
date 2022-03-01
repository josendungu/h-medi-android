package com.sylvia.h_medi.domain.model

import java.time.LocalDate
import java.time.LocalTime


data class Appointment(
    val time: LocalTime,
    val date: LocalDate,
    val doctor: Doctor,
    val appointmentId: Int,
    val specialist: String
)
