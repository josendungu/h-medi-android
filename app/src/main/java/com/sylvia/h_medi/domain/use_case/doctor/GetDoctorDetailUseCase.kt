package com.sylvia.h_medi.domain.use_case.doctor

import android.util.Log
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.data.remote.dto.toDoctor
import com.sylvia.h_medi.domain.model.Doctor
import com.sylvia.h_medi.domain.repository.HMediRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDoctorDetailUseCase @Inject constructor(
    private val repository: HMediRepository
) {

    operator fun invoke(doctorId: Int): Flow<Resource<Doctor>> = flow {

        try {
            emit(Resource.Loading())
            val doctor = repository.getDoctorDetails(doctorId).toDoctor()
            emit(Resource.Success<Doctor>(doctor))

        } catch (e: HttpException) {
            emit(Resource.Error<Doctor>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<Doctor>("Couldn't reach the server. Check your internet connection"))
        }

    }

}