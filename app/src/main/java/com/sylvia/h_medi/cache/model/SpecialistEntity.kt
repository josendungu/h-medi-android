package com.sylvia.h_medi.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "specialists")
data class SpecialistEntity(
    @ColumnInfo(name = "specialist")
    val specialist: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int

)