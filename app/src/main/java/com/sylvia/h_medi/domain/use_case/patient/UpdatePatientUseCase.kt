package com.sylvia.h_medi.domain.use_case.patient

import android.util.Log
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.data.remote.dto.toPatient
import com.sylvia.h_medi.domain.model.Patient
import com.sylvia.h_medi.domain.model.PatientUpdate
import com.sylvia.h_medi.domain.repository.HMediRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdatePatientUseCase @Inject constructor(
    private val repository: HMediRepository
) {

    operator fun invoke(patient: PatientUpdate): Flow<Resource<Patient>> = flow {

        try {
            emit(Resource.Loading())
            val updatedPatient = repository.updatePatient(patient).toPatient()
            emit(Resource.Success<Patient>(updatedPatient))

        } catch (e: HttpException) {
            emit(Resource.Error<Patient>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<Patient>("Couldn't reach the server. Check your internet connection"))
        }

    }
}