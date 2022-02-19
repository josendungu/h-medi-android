package com.sylvia.h_medi.presentation.ui.profile

import com.sylvia.h_medi.domain.model.Patient

data class ProfileState(
    val isLoading: Boolean = false,
    val patient: Patient? = null,
    val error: String  = ""
)
