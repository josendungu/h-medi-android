package com.sylvia.h_medi.presentation.ui.dashboard

import com.sylvia.h_medi.domain.model.Specialist

data class SpecialistState(
    val isLoading: Boolean = false,
    val specialists: List<Specialist> = emptyList()
)
