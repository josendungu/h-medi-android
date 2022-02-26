package com.sylvia.h_medi.data.repository

import com.sylvia.h_medi.common.utils.DateUtils
import com.sylvia.h_medi.data.remote.HMediApi
import com.sylvia.h_medi.data.remote.dto.*
import com.sylvia.h_medi.domain.model.*
import com.sylvia.h_medi.domain.repository.HMediRepository
import javax.inject.Inject

class HMediRepositoryImpl @Inject constructor(
    private val api: HMediApi
): HMediRepository {
    override suspend fun registerPatient(patient: Patient): PatientDto {
        return api.registerPatient(
            firstName = patient.firstName,
            lastName = patient.lastName,
            phoneNumber = patient.phoneNumber,
            gender = patient.gender,
            password = patient.password!!
        )
    }

    override suspend fun loginPatient(phoneNumber: String, password: String): LoginDto {
        return api.login(phoneNumber, password)
    }

    override suspend fun updatePatient(patient: PatientUpdate): PatientDto {
        return api.updatePatient(
            patientId = patient.patientId!!,
            firstName = patient.firstName,
            lastName = patient.lastName,
            phoneNumber = patient.phoneNumber,
            gender = patient.gender,
            password = patient.password
        )
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

    override suspend fun createAppointment(appointment: Appointment, patientId: Int): AppointmentDto {
        return api.addAppointment(
            date = DateUtils.dateToLong(appointment.date),
            time = appointment.time,
            patientId = patientId,
            doctorId = appointment.doctor.doctorId
        )
    }

    override suspend fun deleteAppointment(appointmentId: Int): Boolean {
        return api.deleteAppointment(appointmentId)
    }

    override suspend fun updateAppointment(appointment: AppointmentUpdate): Boolean {
        return api.updateAppointment(
            appointmentId = appointment.appointmentId,
            date = (
                    if (appointment.date == null){
                        null
                    } else {
                        DateUtils.dateToLong(appointment.date!!)
                    }),
            time = appointment.time,
            doctorId = appointment.doctor?.doctorId
        )
    }

    override suspend fun getSpecialistList(): List<SpecialistDto> {
        return api.getSpecialists()
    }

    override suspend fun addEmergency(emergency: Emergency): EmergencyDto {
        return api.addEmergency(
            description = emergency.description,
            type = emergency.emergencyType,
            patientId = emergency.patientId
        )
    }
}