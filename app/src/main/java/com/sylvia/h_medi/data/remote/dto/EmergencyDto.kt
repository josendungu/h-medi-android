package com.sylvia.h_medi.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sylvia.h_medi.domain.model.Emergency

data class EmergencyDto(
    val description: String,
    @SerializedName("emergency_type")
    val emergencyType: String,
    @SerializedName("patient_id")
    val patientId: Int,
    val id: Int
)


fun EmergencyDto.toEmergency(): Emergency {
    return Emergency(
        description = description,
        emergencyType = emergencyType,
        patientId = patientId
    )
}