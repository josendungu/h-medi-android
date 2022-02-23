package com.sylvia.h_medi.domain.model

import com.sylvia.h_medi.cache.model.PatientEntity


data class Patient(
    val firstName: String,
    val gender: String,
    val patientId: Int?,
    val lastName: String,
    val phoneNumber: String,
    val password: String? = null
)

fun Patient.toPatientEntity(): PatientEntity {
    return PatientEntity(
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        id = patientId!!,
        phoneNumber = phoneNumber
    )
}


