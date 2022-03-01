package com.sylvia.h_medi.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class AppointmentUpdate(
    var time: LocalTime? = null,
    var date: LocalDate? = null,
    var doctor: Doctor? = null,
    var appointmentId: Int,
    var specialist: String? = null
)
