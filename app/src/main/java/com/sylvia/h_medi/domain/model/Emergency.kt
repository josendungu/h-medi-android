package com.sylvia.h_medi.domain.model

import com.google.gson.annotations.SerializedName

data class Emergency(
    val description: String,
    val emergencyType: String,
    val patientId: Int
)
