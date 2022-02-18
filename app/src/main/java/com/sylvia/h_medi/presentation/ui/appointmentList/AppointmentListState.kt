package com.sylvia.h_medi.presentation.ui.appointmentList

import com.sylvia.h_medi.domain.model.Appointment
import com.sylvia.h_medi.domain.model.Doctor

data class AppointmentListState (
    val isLoading: Boolean = false,
    val error: String = "",
    val appointments: List<Appointment> = emptyList()
)