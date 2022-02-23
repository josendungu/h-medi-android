package com.sylvia.h_medi.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sylvia.h_medi.cache.model.DoctorEntity
import com.sylvia.h_medi.cache.model.PatientEntity
import com.sylvia.h_medi.data.remote.dto.PatientDto

@Database(entities = [PatientEntity::class, DoctorEntity::class, ],version = 1)
abstract class HMediDatabase: RoomDatabase() {

    abstract fun patientDao(): PatientDto

}