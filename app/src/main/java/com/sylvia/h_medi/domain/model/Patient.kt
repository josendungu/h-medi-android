package com.sylvia.h_medi.domain.model


data class Patient(
    val firstName: String,
    val gender: String,
    val patientId: Int?,
    val lastName: String,
    val phoneNumber: String,
    val password: String? = null
)


