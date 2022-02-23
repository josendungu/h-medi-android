package com.sylvia.h_medi.domain.use_case.patient

import android.util.Log
import com.sylvia.h_medi.cache.HMediDao
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.data.remote.dto.toPatient
import com.sylvia.h_medi.domain.model.Patient
import com.sylvia.h_medi.domain.model.toPatientEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddLoggedInPatientUseCase @Inject constructor(
    private val hMediDao: HMediDao
) {

    operator fun invoke(patient: Patient): Flow<Resource<Boolean>> = flow {

        try {
            emit(Resource.Loading())
            hMediDao.deleteAllPatients()
            hMediDao.insertPatient(patient.toPatientEntity())
            emit(Resource.Success<Boolean>(true))

        } catch (e: HttpException) {
            emit(Resource.Error<Boolean>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<Boolean>("Couldn't reach the server. Check your internet connection"))
        }

    }

}