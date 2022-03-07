package com.sylvia.h_medi.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sylvia.h_medi.common.utils.DateUtils
import com.sylvia.h_medi.domain.model.Appointment

data class AppointmentDto(
    val time: String,
    val date: Long,
    val doctor: DoctorDto,
    @SerializedName("id")
    val appointmentId: Int,
    val specialist: String
)

fun AppointmentDto.toAppointment(): Appointment{
    return Appointment(
        time = DateUtils.dbStringToTime(time),
        date = DateUtils.longToDate(date),
        doctor = doctor.toDoctor(),
        appointmentId = appointmentId,
        specialist = specialist
    )
}
