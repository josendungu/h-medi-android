package com.sylvia.h_medi.domain.use_case.doctor

import android.util.Log
import com.sylvia.h_medi.cache.HMediDao
import com.sylvia.h_medi.cache.model.toDoctor
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.Resource
import com.sylvia.h_medi.domain.model.Doctor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTopRatedDoctors@Inject constructor(
    private val hMediDao: HMediDao
) {

    operator fun invoke(): Flow<Resource<List<Doctor>>> = flow {

        try {
            emit(Resource.Loading())
            val doctors = hMediDao.getHigRatingDoctors().map { it.toDoctor() }

            emit(Resource.Success<List<Doctor>>(doctors))

        } catch (e: HttpException) {
            emit(Resource.Error<List<Doctor>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<List<Doctor>>("Couldn't reach the server. Check your internet connection"))
        }

    }

}