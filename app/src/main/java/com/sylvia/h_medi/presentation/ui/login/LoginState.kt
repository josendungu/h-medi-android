package com.sylvia.h_medi.presentation.ui.login

data class LoginState(
    val isLoading: Boolean = false,
    val error: String = "",
    val errorResponse: String = ""
)
