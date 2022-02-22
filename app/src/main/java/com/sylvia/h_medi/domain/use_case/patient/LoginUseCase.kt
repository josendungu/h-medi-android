package com.sylvia.h_medi.domain.use_case.patient

import android.util.Log
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.data.remote.dto.toLogin
import com.sylvia.h_medi.data.remote.dto.toPatient
import com.sylvia.h_medi.domain.model.Login
import com.sylvia.h_medi.domain.model.Patient
import com.sylvia.h_medi.domain.repository.HMediRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: HMediRepository
) {


    operator fun invoke(phoneNumber: String, password: String): Flow<Resource<Login>> = flow {

        try {
            emit(Resource.Loading())
            val loginResponse = repository.loginPatient(phoneNumber, password).toLogin()
            emit(Resource.Success<Login>(loginResponse))

        } catch (e: HttpException) {
            emit(Resource.Error<Login>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<Login>("Couldn't reach the server. Check your internet connection"))
        }

    }

}