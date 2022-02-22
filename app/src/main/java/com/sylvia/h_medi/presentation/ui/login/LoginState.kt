package com.sylvia.h_medi.presentation.ui.login

import com.sylvia.h_medi.domain.model.Patient

data class LoginState(
    val isLoading: Boolean = false,
    val error: String = "",
    val errorResponse: String = "",
    val navigateToScreen: String = "",
)
