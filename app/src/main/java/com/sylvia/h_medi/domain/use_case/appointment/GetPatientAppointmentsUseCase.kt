package com.sylvia.h_medi.domain.use_case.appointment

import android.util.Log
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.common.utils.DateUtils
import com.sylvia.h_medi.data.remote.dto.toAppointment
import com.sylvia.h_medi.domain.model.Appointment
import com.sylvia.h_medi.domain.repository.HMediRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate
import javax.inject.Inject

class GetPatientAppointmentsUseCase @Inject constructor(
    private val repository: HMediRepository
){

    operator fun invoke(patientId: Int): Flow<Resource<List<Appointment>>> = flow {

        try {
            emit(Resource.Loading())
            val upcomingAppointments = mutableListOf<Appointment>()
            val appointments = repository.getPatientAppointments(patientId).map {
                it.toAppointment()
            }

            val todayLong = LocalDate.now()
            appointments.forEach {
                if (it.date >= todayLong){
                    upcomingAppointments.add(it)
                }
            }

            emit(Resource.Success<List<Appointment>>(upcomingAppointments))

        } catch (e: HttpException) {
            emit(Resource.Error<List<Appointment>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<List<Appointment>>("Couldn't reach the server. Check your internet connection"))
        }

    }
}