package com.sylvia.h_medi.presentation.ui.bookAppointment

import com.sylvia.h_medi.domain.model.Appointment

data class BookAppointmentState (
    val isLoading: Boolean = false,
    val error: String = "",
    val bookingSuccess: Boolean = false,
    val bookingStatusActive: Boolean = false,
    val validateError: String = ""
)