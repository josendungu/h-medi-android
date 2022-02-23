package com.sylvia.h_medi.di

import android.content.Context
import androidx.room.Room
import com.sylvia.h_medi.HMediApplication
import com.sylvia.h_medi.cache.HMediDao
import com.sylvia.h_medi.cache.database.HMediDatabase
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.utils.Navigator
import com.sylvia.h_medi.data.remote.HMediApi
import com.sylvia.h_medi.data.repository.HMediRepositoryImpl
import com.sylvia.h_medi.domain.repository.HMediRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): HMediApplication {
        return app as HMediApplication
    }

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


    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return Navigator()
    }

    @Singleton
    @Provides
    fun provideRecipeDatabase(app: HMediApplication): HMediDatabase{
        return Room
            .databaseBuilder(app, HMediDatabase::class.java, Constants.H_MEDI_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideHMediDao(database: HMediDatabase): HMediDao{
        return database.hMediDao()
    }
}