package com.sylvia.h_medi.domain.repository

import com.sylvia.h_medi.data.remote.dto.AppointmentDto
import com.sylvia.h_medi.data.remote.dto.DoctorDto
import com.sylvia.h_medi.data.remote.dto.PatientDto
import com.sylvia.h_medi.data.remote.dto.SpecialistDto
import com.sylvia.h_medi.domain.model.Appointment
import com.sylvia.h_medi.domain.model.Patient

interface HMediRepository {

    suspend fun registerPatient(patient: Patient): PatientDto

    suspend fun loginPatient(phoneNumber: String, password: String): Boolean

    suspend fun updatePatient(patient: Patient): Boolean

    suspend fun getDoctorList(): List<DoctorDto>

    suspend fun getDoctorListBySpecialty(specialistId: Int): List<DoctorDto>

    suspend fun getDoctorDetails(doctorId: Int): DoctorDto

    suspend fun getPatientAppointments(patientId: Int): List<AppointmentDto>

    suspend fun getAppointmentDetails(appointmentId: Int): AppointmentDto

    suspend fun createAppointment(appointment: Appointment): AppointmentDto

    suspend fun deleteAppointment(appointmentId: Int): Boolean

    suspend fun updateAppointment(appointment: Appointment): Boolean

    suspend fun getSpecialistList(): List<SpecialistDto>

}