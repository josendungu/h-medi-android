package com.sylvia.h_medi.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sylvia.h_medi.domain.model.Login

data class LoginDto(
    val success: Boolean,
    @SerializedName("error_message")
    val errorMessage: String,
    @SerializedName("patient")
    val patientDto: PatientDto?
)

fun LoginDto.toLogin(): Login {
    return Login(
        success,
        errorMessage,
        patientDto?.toPatient()
    )
}