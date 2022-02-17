package com.sylvia.h_medi.di

import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.data.remote.HMediApi
import com.sylvia.h_medi.data.repository.HMediRepositoryImpl
import com.sylvia.h_medi.domain.repository.HMediRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHMediApi(): HMediApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HMediApi::class.java)
    }


    @Provides
    @Singleton
    fun provideHMediRepository(api: HMediApi): HMediRepository {
        return HMediRepositoryImpl(api)
    }
}