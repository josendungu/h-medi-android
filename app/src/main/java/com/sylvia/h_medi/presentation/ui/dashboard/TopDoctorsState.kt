package com.sylvia.h_medi.presentation.ui.dashboard

import com.sylvia.h_medi.domain.model.Doctor

data class TopDoctorsState(
    val isLoading: Boolean = false,
    val doctors: List<Doctor> = emptyList()
)