package com.sylvia.h_medi.presentation.ui.dashboard

import com.sylvia.h_medi.domain.model.Doctor
import com.sylvia.h_medi.domain.model.Specialist

data class DashboardState(
    val isDoctorsLoading: Boolean,
    val isSpecialistLoading: Boolean,
    val doctorError: String = "",
    val specialistError: String = "",
    val topDoctors: List<Doctor> = emptyList(),
    val specialists: List<Specialist> = emptyList(),
    val doctorCache: List<Doctor> =  emptyList()
)
