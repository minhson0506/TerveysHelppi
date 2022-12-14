package com.example.terveyshelppi.service.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerciseData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val timeStart: String,
    val distance: Int,
    val duration: String,
    val activeTime: Long,
    val averageSpeed: Double,
    val calories: Int,
    val elevation: Int,
    val heartRate: Double,
)