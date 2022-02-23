package com.sylvia.h_medi.cache.model

import androidx.room.ColumnInfo

data class SpecialistEntity(
    @ColumnInfo(name = "specialist")
    val specialist: String,

    @ColumnInfo(name = "id")
    val id: Int

)