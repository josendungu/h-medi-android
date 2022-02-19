package com.sylvia.h_medi.presentation.ui.bookAppointment

import com.sylvia.h_medi.domain.model.Appointment

data class BookAppointmantState (
    val isLoading: Boolean = false,
    val error: String = "",
    val appointment: Appointment? = null
)