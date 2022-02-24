package com.sylvia.h_medi.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sylvia.h_medi.domain.model.Doctor

@Entity(tableName = "doctors")
data class DoctorEntity(

    @ColumnInfo(name = "featured_image")
    val featured_image: String,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "about")
    val about: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,

    @ColumnInfo(name = "rating")
    val rating: Int,

    @ColumnInfo(name = "specialist")
    val specialist: String,

    @ColumnInfo(name = "email")
    val email: String,

)

fun DoctorEntity.toDoctor(): Doctor {
    return Doctor(
        about = about,
        email = email,
        firstName = firstName,
        gender = gender,
        doctorId = id,
        imageUrl = featured_image,
        lastName = lastName,
        phoneNumber = phoneNumber,
        rating = rating,
        specialist = specialist
    )
}
