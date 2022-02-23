package com.sylvia.h_medi.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sylvia.h_medi.domain.model.Patient


@Entity(tableName = "patients")
data class PatientEntity(

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,

)


fun PatientEntity.toPatient(): Patient {
    return Patient(
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        patientId = id,
        phoneNumber = phoneNumber
    )
}
