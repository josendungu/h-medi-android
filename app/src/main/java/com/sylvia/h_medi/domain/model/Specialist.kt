package com.sylvia.h_medi.domain.model

import com.sylvia.h_medi.cache.model.SpecialistEntity

data class Specialist(
    val specialist_id: Int,
    val specialistName: String
)

fun Specialist.toSpecialistEntity(): SpecialistEntity {
    return SpecialistEntity(
        specialist = specialistName,
        id = specialist_id
    )
}
