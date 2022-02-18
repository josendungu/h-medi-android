package com.sylvia.h_medi.domain.use_case.appointment

import android.util.Log
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.data.remote.dto.toAppointment
import com.sylvia.h_medi.data.remote.dto.toDoctor
import com.sylvia.h_medi.domain.model.Appointment
import com.sylvia.h_medi.domain.model.Doctor
import com.sylvia.h_medi.domain.repository.HMediRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAppointmentDetailUseCase @Inject constructor(
    private val repository: HMediRepository
) {

    operator fun invoke(appointmentId: Int): Flow<Resource<Appointment>> = flow {

        try {
            emit(Resource.Loading())
            val appointment = repository.getAppointmentDetails(appointmentId).toAppointment()
            emit(Resource.Success<Appointment>(appointment))

        } catch (e: HttpException) {
            emit(Resource.Error<Appointment>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<Appointment>("Couldn't reach the server. Check your internet connection"))
        }

    }

}