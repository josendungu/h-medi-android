package com.sylvia.h_medi.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sylvia.h_medi.domain.model.Appointment

data class AppointmentDto(
    val time: String,
    val date: Int,
    val doctor: DoctorDto,
    @SerializedName("id")
    val appointment_id: Int
)

fun AppointmentDto.toAppointment(): Appointment{
    return Appointment(
        time = time,
        date = date,
        doctor = doctor.toDoctor(),
        appointment_id = appointment_id
    )
}
