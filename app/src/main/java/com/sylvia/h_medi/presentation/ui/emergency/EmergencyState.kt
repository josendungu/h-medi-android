package com.sylvia.h_medi.presentation.ui.emergency

data class EmergencyState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isAdded: Boolean = false,
    var validateError: String = ""
)
