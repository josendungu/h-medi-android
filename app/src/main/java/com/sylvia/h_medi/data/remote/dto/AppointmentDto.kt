package com.sylvia.h_medi.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sylvia.h_medi.domain.model.Appointment

data class AppointmentDto(
    val time: String,
    val date: Int,
    val doctor: DoctorDto,
    @SerializedName("id")
    val appointmentId: Int
)

fun AppointmentDto.toAppointment(): Appointment{
    return Appointment(
        time = time,
        date = date,
        doctor = doctor.toDoctor(),
        appointmentId = appointmentId
    )
}
