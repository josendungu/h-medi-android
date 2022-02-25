package com.sylvia.h_medi.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sylvia.h_medi.domain.model.Specialist

@Entity(tableName = "specialists")
data class SpecialistEntity(
    @ColumnInfo(name = "specialist")
    val specialist: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int

)

fun SpecialistEntity.toSpecialist(): Specialist {
    return Specialist(
        specialistName = specialist,
        specialist_id = id
    )
}