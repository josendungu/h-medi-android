package com.sylvia.h_medi.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sylvia.h_medi.domain.model.Specialist

data class SpecialistDto(
    @SerializedName("id")
    val specialist_id: Int,
    @SerializedName("specialist_name")
    val specialistName: String
)

fun SpecialistDto.toSpecialist(): Specialist{
    return Specialist(
        specialist_id = specialist_id,
        specialistName = specialistName
    )
}