package com.sylvia.h_medi.presentation.ui.doctorDetail

import com.sylvia.h_medi.domain.model.Doctor

data class DoctorDetailState(
    val isLoading: Boolean = false,
    val error: String = ""
)