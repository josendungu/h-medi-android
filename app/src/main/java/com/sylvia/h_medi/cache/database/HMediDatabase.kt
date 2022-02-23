package com.sylvia.h_medi.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sylvia.h_medi.cache.HMediDao
import com.sylvia.h_medi.cache.model.DoctorEntity
import com.sylvia.h_medi.cache.model.PatientEntity
import com.sylvia.h_medi.cache.model.SpecialistEntity
import com.sylvia.h_medi.data.remote.dto.PatientDto

@Database(entities = [PatientEntity::class, DoctorEntity::class, SpecialistEntity::class],version = 1)
abstract class HMediDatabase: RoomDatabase() {

    abstract fun hMediDao(): HMediDao

}