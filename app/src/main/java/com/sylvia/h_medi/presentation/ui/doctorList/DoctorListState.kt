package com.sylvia.h_medi.presentation.ui.doctorList

import com.sylvia.h_medi.domain.model.Doctor

data class DoctorListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val doctors: List<Doctor> = emptyList()
)