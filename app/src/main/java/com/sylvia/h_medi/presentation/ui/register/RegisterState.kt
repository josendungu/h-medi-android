package com.sylvia.h_medi.presentation.ui.register

import com.sylvia.h_medi.domain.model.Patient

data class RegisterState(

    val isLoading: Boolean = false,
    val error: String = "",
    val patient: Patient? = null

)