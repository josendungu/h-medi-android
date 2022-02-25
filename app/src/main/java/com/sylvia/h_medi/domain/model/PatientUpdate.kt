package com.sylvia.h_medi.domain.model

data class PatientUpdate(
    var firstName: String? = null,
    var gender: String? = null,
    var patientId: Int? = null,
    var lastName: String? = null,
    var phoneNumber: String? = null,
    var password: String? = null
)
