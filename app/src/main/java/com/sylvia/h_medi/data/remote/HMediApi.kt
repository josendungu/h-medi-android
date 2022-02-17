package com.sylvia.h_medi.data.remote

import com.sylvia.h_medi.data.remote.dto.AppointmentDto
import com.sylvia.h_medi.data.remote.dto.DoctorDto
import com.sylvia.h_medi.data.remote.dto.PatientDto
import com.sylvia.h_medi.data.remote.dto.SpecialistDto
import com.sylvia.h_medi.domain.model.Appointment
import com.sylvia.h_medi.domain.model.Patient
import retrofit2.http.*

interface HMediApi {

    @POST("patients")
    suspend fun registerPatient(
        @Body patient: Patient
    ): PatientDto

    @PUT("patients/{patientId}")
    suspend fun updatePatient(
        @Path("patientId") patientId: Int,
        @Body patient: Patient
    ): Boolean

    @POST("login")
    suspend fun login(
        @Body phoneNumber: String,
        @Body password: String
    ): Boolean




    @GET("doctors/{doctorId}")
    suspend fun getDoctorById(
        @Path("doctorId") doctorId: Int
    ): DoctorDto

    @GET("doctors")
    suspend fun getAllDoctors(): List<DoctorDto>

    @GET("doctors/specialist/{specialistId}")
    suspend fun getDoctorsBySpecialistId(
        @Path("specialistId") specialistId: Int
    ): List<DoctorDto>




    @GET("appointments/patient/{patientId}")
    suspend fun getUpcomingAppointments(
        @Path("patientId") patientId: Int,
    ): List<AppointmentDto>

    @GET("appointments/{appointmentId}")
    suspend fun getAppointmentDetails(
        @Path("appointmentId") appointmentId: Int
    ): AppointmentDto

    @POST("appointments")
    suspend fun addAppointment(
        @Body appointment: Appointment
    ): AppointmentDto

    @DELETE("appointments/{appointmentId}")
    suspend fun deleteAppointment(
        @Path("appointmentId") appointmentId: Int
    ): Boolean

    @PUT("appointments/{appointmentId}")
    suspend fun updateAppointment(
        @Path("appointmentId") appointmentId: Int,
        @Body appointment: Appointment
    ): Boolean




    @GET("specialists")
    suspend fun getSpecialists(): List<SpecialistDto>
}