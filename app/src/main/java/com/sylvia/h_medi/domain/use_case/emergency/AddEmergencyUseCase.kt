package com.sylvia.h_medi.domain.use_case.emergency

import android.util.Log
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.data.remote.dto.toEmergency
import com.sylvia.h_medi.domain.model.Emergency
import com.sylvia.h_medi.domain.repository.HMediRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddEmergencyUseCase @Inject constructor(
    private val repository: HMediRepository
) {

    operator fun invoke(emergency: Emergency): Flow<Resource<Emergency>> = flow {

        try {
            emit(Resource.Loading())
            val emergencyCreated = repository.addEmergency(emergency).toEmergency()
            emit(Resource.Success<Emergency>(emergencyCreated))

        } catch (e: HttpException) {
            emit(Resource.Error<Emergency>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<Emergency>("Couldn't reach the server. Check your internet connection"))
        }

    }

}