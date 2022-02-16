package com.sylvia.h_medi.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sylvia.h_medi.domain.model.Doctor

data class DoctorDto(
    val about: String,

    val email: String,

    @SerializedName("first_name")
    val firstName: String,

    val gender: String,

    @SerializedName("id")
    val doctorId: Int,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("phone_number")
    val phoneNumber: String,

    val rating: Int,

    @SerializedName("specialist_id")
    val specialistId: Int
)


fun DoctorDto.toDoctor(): Doctor {
    return Doctor(
        about = about,
        email = email,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        doctorId = doctorId,
        imageUrl = imageUrl,
        phoneNumber = phoneNumber,
        rating = rating,
        specialistId = specialistId
    )
}