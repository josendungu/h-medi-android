package com.sylvia.h_medi.presentation.ui.dashboard

import com.sylvia.h_medi.domain.model.Doctor
import com.sylvia.h_medi.domain.model.Patient
import com.sylvia.h_medi.domain.model.Specialist

data class DashboardState(
    val isLoading: Boolean = false,
    val error: String = "",
    val patientFirstName: String = ""
)
