package com.sylvia.h_medi.domain.use_case.specialist

import android.util.Log
import com.sylvia.h_medi.cache.HMediDao
import com.sylvia.h_medi.cache.model.toSpecialist
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.data.remote.dto.toSpecialist
import com.sylvia.h_medi.domain.model.Specialist
import com.sylvia.h_medi.domain.model.toSpecialistEntity
import com.sylvia.h_medi.domain.repository.HMediRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSpecialistDetailsUSeCase @Inject constructor(
    private val hMediDao: HMediDao
) {

    operator fun invoke(specialistId: Int): Flow<Resource<Specialist?>> = flow {

        try {
            emit(Resource.Loading())
            val specialist = hMediDao.getSpecialistById(specialistId)?.toSpecialist()
            emit(Resource.Success<Specialist?>(specialist))

        } catch (e: HttpException) {
            emit(Resource.Error<Specialist?>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<Specialist?>("Couldn't reach the server. Check your internet connection"))
        }

    }

}