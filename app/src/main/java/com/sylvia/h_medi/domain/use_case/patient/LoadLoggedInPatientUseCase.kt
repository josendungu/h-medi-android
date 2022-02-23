package com.sylvia.h_medi.domain.use_case.patient

import android.util.Log
import com.sylvia.h_medi.cache.HMediDao
import com.sylvia.h_medi.cache.model.toPatient
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.data.remote.dto.toLogin
import com.sylvia.h_medi.domain.model.Login
import com.sylvia.h_medi.domain.model.Patient
import com.sylvia.h_medi.domain.repository.HMediRepository
import com.sylvia.h_medi.presentation.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoadLoggedInPatientUseCase @Inject constructor(
    private val hMediDao: HMediDao,
    private val navigator: Navigator
) {

    operator fun invoke(): Flow<Resource<Patient?>> = flow {

        try {
            emit(Resource.Loading())
            val patient = hMediDao.getPatient()

            emit(Resource.Success<Patient?>(patient?.toPatient()))



        } catch (e: HttpException) {
            emit(Resource.Error<Patient?>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<Patient?>("Couldn't reach the server. Check your internet connection"))
        }

    }
}