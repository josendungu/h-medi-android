package com.sylvia.h_medi.domain.model

import java.util.*

data class AppointmentUpdate(
    var time: String? = null,
    var date: Date? = null,
    var doctor: Doctor? = null,
    var appointmentId: Int,
    var specialist: String? = null
)
