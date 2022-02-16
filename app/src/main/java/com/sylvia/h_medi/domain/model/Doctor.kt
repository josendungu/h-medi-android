package com.sylvia.h_medi.domain.model


data class Doctor(
    val about: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val doctorId: Int,
    val imageUrl: String,
    val lastName: String,
    val phoneNumber: String,
    val rating: Int,
    val specialistId: Int
)
