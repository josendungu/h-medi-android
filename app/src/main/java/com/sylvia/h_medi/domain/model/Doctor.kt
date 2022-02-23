package com.sylvia.h_medi.domain.model

import com.sylvia.h_medi.cache.model.DoctorEntity


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

fun Doctor.toDoctorEntity(): DoctorEntity{
    return DoctorEntity(
        featured_image = imageUrl,
        firstName = firstName,
        lastName = lastName,
        id = doctorId,
        about = about,
        gender = gender,
        phoneNumber = phoneNumber,
        rating = rating,
        specialistId = specialistId,
        email = email
    )
}
