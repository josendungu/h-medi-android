package com.sylvia.h_medi.domain.repository

import com.sylvia.h_medi.data.remote.dto.*
import com.sylvia.h_medi.domain.model.*

interface HMediRepository {

    suspend fun registerPatient(patient: Patient): PatientDto

    suspend fun loginPatient(phoneNumber: String, password: String): LoginDto

    suspend fun updatePatient(patient: PatientUpdate): PatientDto

    suspend fun getDoctorList(): List<DoctorDto>

    suspend fun getDoctorListBySpecialty(specialistId: Int): List<DoctorDto>

    suspend fun getDoctorDetails(doctorId: Int): DoctorDto

    suspend fun getPatientAppointments(patientId: Int): List<AppointmentDto>

    suspend fun getAppointmentDetails(appointmentId: Int): AppointmentDto

    suspend fun createAppointment(appointment: Appointment, patientId: Int): AppointmentDto

    suspend fun deleteAppointment(appointmentId: Int): Boolean

    suspend fun updateAppointment(appointment: AppointmentUpdate): Int

    suspend fun getSpecialistList(): List<SpecialistDto>

    suspend fun addEmergency(emergency: Emergency): EmergencyDto

}