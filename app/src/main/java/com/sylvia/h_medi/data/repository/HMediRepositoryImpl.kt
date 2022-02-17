package com.sylvia.h_medi.data.repository

import com.sylvia.h_medi.data.remote.HMediApi
import com.sylvia.h_medi.data.remote.dto.*
import com.sylvia.h_medi.domain.model.Appointment
import com.sylvia.h_medi.domain.model.Patient
import com.sylvia.h_medi.domain.repository.HMediRepository
import javax.inject.Inject

class HMediRepositoryImpl @Inject constructor(
    private val api: HMediApi
): HMediRepository {
    override suspend fun registerPatient(patient: Patient): PatientDto {
        return api.registerPatient(
            patient.firstName,
            patient.lastName,
            patient.phoneNumber,
            patient.gender,
            patient.password!!
        )
    }

    override suspend fun loginPatient(phoneNumber: String, password: String): LoginDto {
        return api.login(phoneNumber, password)
    }

    override suspend fun updatePatient(patient: Patient): Boolean {
        return api.updatePatient(patient.patientId!!,patient)
    }

    override suspend fun getDoctorList(): List<DoctorDto> {
        return api.getAllDoctors()
    }

    override suspend fun getDoctorListBySpecialty(specialistId: Int): List<DoctorDto> {
        return api.getDoctorsBySpecialistId(specialistId)
    }

    override suspend fun getDoctorDetails(doctorId: Int): DoctorDto {
        return api.getDoctorById(doctorId)
    }

    override suspend fun getPatientAppointments(patientId: Int): List<AppointmentDto> {
        return api.getUpcomingAppointments(patientId)
    }

    override suspend fun getAppointmentDetails(appointmentId: Int): AppointmentDto {
        return  api.getAppointmentDetails(appointmentId)
    }

    override suspend fun createAppointment(appointment: Appointment): AppointmentDto {
        return api.addAppointment(appointment)
    }

    override suspend fun deleteAppointment(appointmentId: Int): Boolean {
        return api.deleteAppointment(appointmentId)
    }

    override suspend fun updateAppointment(appointment: Appointment): Boolean {
        return api.updateAppointment(appointment.appointmentId, appointment)
    }

    override suspend fun getSpecialistList(): List<SpecialistDto> {
        return api.getSpecialists()
    }
}