package com.sylvia.h_medi.presentation.ui.dashboard

import com.sylvia.h_medi.domain.model.Doctor

data class DashDoctorsState(
    val isLoading: Boolean = false,
    val doctors: List<Doctor> = emptyList()
)
