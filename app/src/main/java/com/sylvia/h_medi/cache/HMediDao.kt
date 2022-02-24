package com.sylvia.h_medi.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sylvia.h_medi.cache.model.DoctorEntity
import com.sylvia.h_medi.cache.model.PatientEntity
import com.sylvia.h_medi.cache.model.SpecialistEntity
import com.sylvia.h_medi.domain.model.Specialist


@Dao
interface HMediDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patientEntity: PatientEntity): Long

    @Query("""
        SELECT * FROM patients LIMIT 1
        """)
    suspend fun getPatient(): PatientEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoctors(doctorEntity: List<DoctorEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecialists(specialistsEntity: List<SpecialistEntity>): LongArray

    @Query("SELECT * FROM doctors WHERE id = :id")
    suspend fun getDoctorById(id: Int): DoctorEntity

    @Query("""
        SELECT * FROM doctors
        ORDER BY rating DESC LIMIT 2
        """)
    suspend fun getHigRatingDoctors(): List<DoctorEntity>

    @Query("""
        SELECT * FROM doctors
        ORDER BY id
        """)
    suspend fun getAllDoctors(): List<DoctorEntity>


    @Query("""
        SELECT * FROM doctors
        ORDER BY id DESC LIMIT 5
        """)
    suspend fun getDoctorsForDash(): List<DoctorEntity>


    @Query("""
        SELECT * FROM doctors
        WHERE specialist = :specialist
        """)
    suspend fun getDoctorsBySpeciality(specialist:String): List<DoctorEntity>

    @Query("""
        SELECT * FROM specialists
        """)
    suspend fun getAllSpecialists(): List<SpecialistEntity>

    @Query("""
        DELETE FROM patients
        """)
    suspend fun deleteAllPatients()







}