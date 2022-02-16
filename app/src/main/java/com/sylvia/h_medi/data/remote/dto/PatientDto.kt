package com.sylvia.h_medi.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sylvia.h_medi.domain.model.Patient

data class PatientDto(
    @SerializedName("first_name")
    val firstName: String,

    val gender: String,

    @SerializedName("id")
    val patientId: Int,

    @SerializedName("last_name")
    val lastName: String,

    val password: String,

    @SerializedName("phone_number")
    val phoneNumber: String
)

fun PatientDto.toPatient(): Patient{
    return Patient(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        gender = gender,
        patientId = patientId
    )
}