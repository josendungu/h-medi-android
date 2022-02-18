package com.sylvia.h_medi.presentation.ui.appointmentDetail

import com.sylvia.h_medi.domain.model.Appointment

data class AppointmentDetailState (
    val isLoading: Boolean = false,
    val error: String = "",
    val appointment: Appointment? = null
)