package com.sylvia.h_medi.domain.use_case.doctor

import android.util.Log
import com.sylvia.h_medi.cache.HMediDao
import com.sylvia.h_medi.cache.model.toDoctor
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

class GetDoctorSearchListUseCase @Inject constructor(
    private val hMediDao: HMediDao
) {

    operator fun invoke(searchString: String, specialist: String? = null): Flow<Resource<List<Doctor>>> = flow {

        try {
            emit(Resource.Loading())
            if (specialist != null) {
                val doctor = hMediDao.getDoctorsBySearchAndSpecialization(searchString, specialist).map { it.toDoctor() }
                emit(Resource.Success<List<Doctor>>(doctor))
            } else {
                val doctor = hMediDao.getDoctorsBySearch(searchString).map { it.toDoctor() }
                emit(Resource.Success<List<Doctor>>(doctor))
            }

        } catch (e: HttpException) {
            emit(Resource.Error<List<Doctor>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.d(Constants.TAG, "invoke: ${e.localizedMessage}")
            emit(Resource.Error<List<Doctor>>("Couldn't reach the server. Check your internet connection"))
        }

    }

}